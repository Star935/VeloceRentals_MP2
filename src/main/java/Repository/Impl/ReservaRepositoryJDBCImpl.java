package Repository.Impl;

import Config.DatabaseConnection;
import Model.EstadoReserva;
import Model.Reserva;
import Model.Usuario;
import Model.Vehiculo;
import Repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryJDBCImpl implements Repository<Reserva> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }
    Reserva r =  new Reserva();

    private Reserva createreserva (ResultSet rs) throws SQLException {
        Reserva r =  new Reserva();

        UsuarioRepositoryJDBCImpl usrepo = new UsuarioRepositoryJDBCImpl();
        VehiculoRepositoryJDBCImpl vehrepo = new VehiculoRepositoryJDBCImpl();

        r.setId(rs.getInt("id"));
        int id_usuario = rs.getInt("id_usuario");
        Usuario u = usrepo.byId(id_usuario);
        r.setUsuario(u);
        int id_vehiculo = rs.getInt("id_vehiculo");
        Vehiculo v = vehrepo.byId(id_vehiculo);
        r.setVehiculo(v);
        r.setFecha_inicio(rs.getDate("fecha_inicio"));
        r.setFecha_fin(rs.getDate("fecha_fin"));
        r.setEstado(EstadoReserva.getTypeByValue(rs.getInt("estado")));
        return r;

    }
    @Override
    public List<Reserva> list() {
        List<Reserva> list = new ArrayList<>();
        try (Statement st= getConnection().createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM reservaciones;"
             )){

            while(rs.next()){
                Reserva x = createreserva(rs);
                list.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Reserva byId(int id) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "SELECT * FROM reservaciones WHERE id =?;"
                )){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                r = createreserva(rs);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public void save(Reserva re) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "INSERT INTO reservaciones (id, id_usuario, id_vehiculo, fecha_inicio, fecha_fin, estado) VALUES (?,?,?,?,?,?) "
                )) {
            ps.setInt(1,re.getId());
            ps.setInt(2,re.getUsuario().getId());
            ps.setInt(3,re.getVehiculo().getId());
            ps.setDate(4, (Date) re.getFecha_inicio());
            ps.setDate(5, (Date) re.getFecha_fin());
            ps.setString(6, String.valueOf(re.getEstado()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = getConnection()
                .prepareStatement("""
                                      DELETE FROM reservaciones where id=?
                                      """)
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Reserva re) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "UPDATE reservaciones SET id_usuario=?, id_vehiculo=?, fecha_inicio=?, fecha_fin=?, estado=? WHERE id=?"
                )){
            ps.setInt(1,re.getUsuario().getId());
            ps.setInt(2,re.getVehiculo().getId());
            ps.setDate(3, (Date) re.getFecha_inicio());
            ps.setDate(4, (Date) re.getFecha_fin());
            ps.setString(5, String.valueOf(re.getEstado()));
            ps.setInt(6,re.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
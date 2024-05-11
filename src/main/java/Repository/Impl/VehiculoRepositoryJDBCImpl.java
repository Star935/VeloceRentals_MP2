package Repository.Impl;

import Config.DatabaseConnection;
import Model.EstadoVehiculo;
import Model.Vehiculo;
import Repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoRepositoryJDBCImpl implements Repository<Vehiculo> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }
    Vehiculo v = new Vehiculo();

    private Vehiculo createvehiculo (ResultSet rs) throws SQLException {
        Vehiculo v = new Vehiculo();
        v.setId(rs.getInt("id"));
        v.setTipoVehiculo(rs.getString("tipoVehiculo"));
        v.setModelo(rs.getString("modelo"));
        v.setMarca(rs.getString("marca"));
        v.setPlaca(rs.getString("placa"));
        v.setPrecio_por_dia(rs.getInt("precio_por_dia"));
        v.setDisponibilidad(EstadoVehiculo.valueOf(rs.getString("disponibilidad")));
        return v;
    }

    @Override
    public List<Vehiculo> list() {
        List<Vehiculo> list = new ArrayList<>();
        try (Statement st= getConnection().createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM vehiculos;"
             )){

            while(rs.next()){
                Vehiculo x = createvehiculo(rs);
                list.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Vehiculo byId(int id) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "SELECT * FROM vehiculos WHERE id =?;"
                )){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                v = createvehiculo(rs);
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void save(Vehiculo ve) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "INSERT INTO vehiculos (id, tipoVehiculo, modelo, marca, placa, precio_por_dia,disponibilidad) VALUES (?,?,?,?,?,?,?) "
                )) {
            ps.setInt(1,ve.getId());
            ps.setString(2,ve.getTipoVehiculo());
            ps.setString(3,ve.getModelo());
            ps.setString(4,ve.getMarca());
            ps.setString(5,ve.getPlaca());
            ps.setInt(6,ve.getPrecio_por_dia());
            ps.setString(7, String.valueOf(ve.getDisponibilidad()));
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = getConnection()
                .prepareStatement("""
                                      DELETE FROM vehiculos where id=?
                                      """)
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehiculo ve) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "UPDATE vehiculos SET tipoVehiculo=?, modelo=?, marca=?, placa=?, precio_por_dia=?, disponibilidad=? WHERE id=?"
                )){
                ps.setString(1,ve.getTipoVehiculo());
                ps.setString(2,ve.getModelo());
                ps.setString(3,ve.getMarca());
                ps.setString(4,ve.getPlaca());
                ps.setInt(5,ve.getPrecio_por_dia());
                ps.setString(6, String.valueOf(ve.getDisponibilidad()));
                ps.setInt(7,ve.getId());
                ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

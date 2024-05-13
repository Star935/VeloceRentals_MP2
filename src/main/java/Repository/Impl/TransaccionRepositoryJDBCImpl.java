package Repository.Impl;

import Config.DatabaseConnection;
import Model.EstadoTransaccion;
import Model.Reserva;
import Model.Transaccion;
import Repository.Impl.ReservaRepositoryJDBCImpl;
import Repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionRepositoryJDBCImpl implements Repository<Transaccion> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }
    Transaccion t = new Transaccion();

    private Transaccion createtransaccion (ResultSet rs) throws SQLException {
        Transaccion t = new Transaccion();
        ReservaRepositoryJDBCImpl resrepo = new ReservaRepositoryJDBCImpl();

        t.setId(rs.getInt("id"));
        int id_reserva = rs.getInt("id_reserva");
        Reserva r = resrepo.byId(id_reserva);
        t.setReserva(r);
        t.setMonto(rs.getInt("monto"));
        t.setMetodo_pago(rs.getString("metodo_pago"));
        t.setFecha(rs.getDate("fecha"));
        t.setEstado(EstadoTransaccion.valueOf(rs.getString("estado")));
        return t;
    }
    @Override
    public List<Transaccion> list() {
        List<Transaccion> list = new ArrayList<>();
        try (Statement st= getConnection().createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM transacciones;"
             )){

            while(rs.next()){
                Transaccion x = createtransaccion(rs);
                list.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Transaccion byId(int id) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "SELECT * FROM transacciones WHERE id =?;"
                )){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                t = createtransaccion(rs);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void save(Transaccion tr) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "INSERT INTO transacciones (id, id_reserva, monto, metodo_pago, fecha_hora, estado) VALUES (?,?,?,?,?,?) "
                )) {
            ps.setInt(1,tr.getId());
            ps.setInt(2,tr.getReserva().getId());
            ps.setInt(3,tr.getMonto());
            ps.setString(4,tr.getMetodo_pago());
            ps.setDate(5,tr.getFecha());
            ps.setString(6, String.valueOf(tr.getEstado()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = getConnection()
                .prepareStatement("""
                                      DELETE FROM transacciones where id=?
                                      """)
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Transaccion tr) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                        "UPDATE transacciones SET id_reserva=?, monto=?, metodo_pago=?, fecha_hora=?, estado=? WHERE id=?"
                )){
            ps.setInt(1,tr.getReserva().getId());
            ps.setInt(2,tr.getMonto());
            ps.setString(3,tr.getMetodo_pago());
            ps.setDate(4,tr.getFecha());
            ps.setString(5, String.valueOf(tr.getEstado()));
            ps.setInt(6,tr.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package Repository.Impl;

import Config.DatabaseConnection;
import Model.EstadoUsuario;
import Model.Usuario;
import Repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryJDBCImpl implements Repository<Usuario> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }
    Usuario u = new Usuario();

    private Usuario createUsuario(ResultSet rs ) throws SQLException{
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setCedula(rs.getString("cedula"));
        u.setNombre_completo(rs.getString("nombre_completo"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
        return u;
    }
    @Override
    public List<Usuario> list() {
        List<Usuario> ulist = new ArrayList<>();
        try (Statement st= getConnection().createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM usuarios;"
             )){

            while(rs.next()){
                Usuario us = createUsuario(rs);
                ulist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ulist;
    }

    @Override
    public Usuario byId(int id) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                         "SELECT * FROM usuarios WHERE id =?;"
                )){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                u = createUsuario(rs);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void save(Usuario us) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                 "INSERT INTO usuarios (id, cedula, nombre_completo, email, password, estado) VALUES (?,?,?,?,?,?) "
                )) {
            ps.setInt(1, us.getId());
            ps.setString(2, us.getCedula());
            ps.setString(3, us.getNombre_completo());
            ps.setString(4, us.getEmail());
            ps.setString(5, us.getPassword());
            ps.setString(6, String.valueOf(us.getEstado()));
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = getConnection()
                .prepareStatement("""
                                      DELETE FROM usuarios where id=?
                                      """)
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Usuario us) {
        try (PreparedStatement ps = getConnection()
                .prepareStatement(
                         "UPDATE usuarios SET cedula=?, nombre_completo=?, email=?, password=?, estado=? WHERE id=?"
                )){
            ps.setString(1, us.getCedula());
            ps.setString(2, us.getNombre_completo());
            ps.setString(3, us.getEmail());
            ps.setString(4, us.getPassword());
            ps.setString(5, String.valueOf(us.getEstado()));
            ps.setInt(6, us.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
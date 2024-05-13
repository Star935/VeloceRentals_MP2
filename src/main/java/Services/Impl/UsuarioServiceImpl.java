package Services.Impl;

import Mapping.Dtos.UsuarioDto;
import Mapping.Mappers.UsuarioMapper;
import Model.Usuario;
import Repository.Repository;
import Repository.Impl.UsuarioRepositoryJDBCImpl;
import Services.Service;

import java.util.List;

public class UsuarioServiceImpl implements Service<UsuarioDto> {

    private final Repository<Usuario> repository;

    public UsuarioServiceImpl() {this.repository = new UsuarioRepositoryJDBCImpl();}

    @Override
    public List<UsuarioDto> list() {
        return repository.list()
                .stream()
                .map(UsuarioMapper::mapFromModel)
                .toList();
    }

    @Override
    public UsuarioDto byId(int id) {
        Usuario usuario = repository.byId(id);
        return UsuarioMapper.mapFromModel(usuario);
    }

    @Override
    public void save(UsuarioDto usuarioDto) {
        repository.save(UsuarioMapper.mapFromDto(usuarioDto));
    }

    @Override
    public void delete(int id) {repository.delete(id);}

    @Override
    public void update(UsuarioDto usuarioDto) {
            // Obtener el usuario existente de la base de datos utilizando el id
            Usuario usuarioExistente = repository.byId(usuarioDto.id());

            // Actualizar los campos del usuario existente con los datos del usuarioDto proporcionado
            usuarioExistente.setCedula(usuarioDto.cedula());
            usuarioExistente.setNombre_completo(usuarioDto.nombre_completo());
            usuarioExistente.setEmail(usuarioDto.email());
            usuarioExistente.setPassword(usuarioDto.password());
            usuarioExistente.setEstado(usuarioDto.estado());

            // Guardar el usuario actualizado en la base de datos
            repository.update(usuarioExistente);
    }
}
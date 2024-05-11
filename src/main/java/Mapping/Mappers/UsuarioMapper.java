package Mapping.Mappers;

import Mapping.Dtos.UsuarioDto;
import Model.Usuario;

public class UsuarioMapper {
    public static UsuarioDto mapFromModel (Usuario usuario){
        return  new UsuarioDto(usuario.getId(), usuario.getCedula(), usuario.getNombre_completo(), usuario.getEmail(), usuario.getPassword(), usuario.getEstado());
    }
    public static Usuario mapFromDto (UsuarioDto usuarioDto){
        return Usuario.builder()
                .id(usuarioDto.id())
                .cedula(usuarioDto.cedula())
                .nombre_completo(usuarioDto.nombre_completo())
                .email(usuarioDto.email())
                .password(usuarioDto.password())
                .estado(usuarioDto.estado())
                .build();
    }
}

package Mapping.Dtos;

import Model.EstadoUsuario;

public record UsuarioDto(Integer id, String cedula, String nombre_completo, String email, String password, EstadoUsuario estado) {
}

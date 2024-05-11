package Model;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Usuario {
    private Integer id;
    private String cedula;
    private String nombre_completo;
    private String email;
    private String password;
    private EstadoUsuario estado;
}

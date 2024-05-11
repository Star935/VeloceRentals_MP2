package Model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Reserva {
    private Integer id;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private EstadoReserva estado;
}

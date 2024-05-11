package Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Vehiculo {
    private Integer id;
    private String tipoVehiculo;
    private String modelo;
    private String marca;
    private String placa;
    private Integer precio_por_dia;
    private EstadoVehiculo disponibilidad;

}

package Mapping.Dtos;

import Model.EstadoReserva;
import Model.Usuario;
import Model.Vehiculo;

import java.util.Date;

public record ReservaDto(Integer id, Usuario usuario, Vehiculo vehiculo, Date fecha_inicio, Date fecha_fin, EstadoReserva estado) {
}

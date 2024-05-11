package Mapping.Dtos;

import Model.EstadoTransaccion;
import Model.Reserva;

import java.sql.Date;
import java.sql.Timestamp;

public record TransaccionDto(Integer id, Reserva reserva, Integer monto, String metodo_pago, Date fecha, EstadoTransaccion estado) {
}

package Mapping.Dtos;

import Model.EstadoVehiculo;

public record VehiculoDto (Integer id, String tipoVehiculo, String modelo , String marca, String placa, Integer  precio_por_dia, EstadoVehiculo disponibilidad) {
}

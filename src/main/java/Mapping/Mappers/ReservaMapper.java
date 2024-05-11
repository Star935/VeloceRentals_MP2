package Mapping.Mappers;

import Mapping.Dtos.ReservaDto;
import Model.Reserva;

public class ReservaMapper {
    private static  ReservaDto mapFromModel (Reserva reserva){
        return new ReservaDto(reserva.getId(), reserva.getUsuario(), reserva.getVehiculo(), reserva.getFecha_inicio(),reserva.getFecha_fin(),reserva.getEstado());
    }

    private static  Reserva mapFromDto (ReservaDto reservaDto){
        return Reserva.builder()
                .id(reservaDto.id())
                .usuario(reservaDto.usuario())
                .vehiculo(reservaDto.vehiculo())
                .fecha_inicio(reservaDto.fecha_inicio())
                .fecha_fin(reservaDto.fecha_fin())
                .estado(reservaDto.estado())
                .build();
    }
}

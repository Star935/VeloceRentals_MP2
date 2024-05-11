package Mapping.Mappers;

import Mapping.Dtos.TransaccionDto;
import Model.Transaccion;

public class TransaccionMapper {
    private  static TransaccionDto mapFromModel (Transaccion transaccion){
       return new TransaccionDto(transaccion.getId(), transaccion.getReserva(), transaccion.getMonto(), transaccion.getMetodo_pago(), transaccion.getFecha(), transaccion.getEstado());
    }
    private static Transaccion mapFromDto(TransaccionDto transaccionDto){
        return  Transaccion.builder()
                .id(transaccionDto.id())
                .reserva(transaccionDto.reserva())
                .monto(transaccionDto.monto())
                .metodo_pago(transaccionDto.metodo_pago())
                .fecha(transaccionDto.fecha())
                .estado(transaccionDto.estado())
                .build();
    }
}

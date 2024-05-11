package Mapping.Mappers;

import Mapping.Dtos.VehiculoDto;
import Model.Vehiculo;

public class VehiculoMapper {
    public static VehiculoDto mapFromModel(Vehiculo vehiculo){
        return new VehiculoDto (vehiculo.getId(), vehiculo.getTipoVehiculo(), vehiculo.getModelo(), vehiculo.getMarca(), vehiculo.getPlaca(), vehiculo.getPrecio_por_dia(), vehiculo.getDisponibilidad());
    }
    public static Vehiculo mapFromDto(VehiculoDto vehiculoDto){
        return  Vehiculo.builder()
                .id(vehiculoDto.id())
                .tipoVehiculo(vehiculoDto.tipoVehiculo())
                .modelo(vehiculoDto.modelo())
                .marca(vehiculoDto.marca())
                .placa(vehiculoDto.placa())
                .precio_por_dia(vehiculoDto.precio_por_dia())
                .disponibilidad(vehiculoDto.disponibilidad())
                .build();
    }
}

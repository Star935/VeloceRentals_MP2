package Services.Impl;

import Mapping.Dtos.VehiculoDto;
import Mapping.Mappers.VehiculoMapper;
import Model.Usuario;
import Model.Vehiculo;
import Repository.Impl.VehiculoRepositoryJDBCImpl;
import Repository.Repository;
import Services.Service;

import java.util.List;

public class VehiculoServiceImpl implements Service<VehiculoDto> {
    private final Repository<Vehiculo> repository;

    public VehiculoServiceImpl() {this.repository = new VehiculoRepositoryJDBCImpl();}

    @Override
    public List<VehiculoDto> list() {
        return repository.list()
                .stream()
                .map(VehiculoMapper:: mapFromModel)
                .toList();
    }

    @Override
    public VehiculoDto byId(int id) {
        Vehiculo vehiculo = repository.byId(id);
        return VehiculoMapper.mapFromModel(vehiculo);
    }

    @Override
    public void save(VehiculoDto vehiculoDto) {
        repository.save(VehiculoMapper
                .mapFromDto(vehiculoDto));
    }

    @Override
    public void delete(int id) {repository.delete(id);}

    @Override
    public void update(VehiculoDto vehiculoDto) {
        Vehiculo vehiculo = VehiculoMapper.mapFromDto(vehiculoDto);
        repository.update(vehiculo);
    }
}

package info.mastera.service;

import info.mastera.model.Warehouse;

import java.util.List;

public interface IWarehouseService {

    Warehouse create(Warehouse entity);

    void delete(Warehouse entity);

    void update(Warehouse entity);

    Warehouse getById(int id);

    List<Warehouse> getAll();

    Long count();

    List<Warehouse> getAllWithPart();

    Warehouse getByIdWithPart(int id);

}

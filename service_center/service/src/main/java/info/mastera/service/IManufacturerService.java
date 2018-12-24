package info.mastera.service;

import info.mastera.model.Manufacturer;

import java.util.List;

public interface IManufacturerService {

    Manufacturer create(Manufacturer entity);

    void delete(Manufacturer entity);

    void update(Manufacturer entity);

    Manufacturer getById(int id);

    List<Manufacturer> getAll();

    Long count();
}

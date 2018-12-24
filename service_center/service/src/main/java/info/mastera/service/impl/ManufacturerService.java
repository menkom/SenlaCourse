package info.mastera.service.impl;

import info.mastera.dao.IManufacturerDao;
import info.mastera.model.Manufacturer;
import info.mastera.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManufacturerService implements IManufacturerService {

    @Autowired
    private IManufacturerDao<Manufacturer> manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer entity) {
        return manufacturerDao.create(entity);
    }

    @Override
    public void delete(Manufacturer entity) {
        manufacturerDao.delete(entity);
    }

    @Override
    public void update(Manufacturer entity) {
        manufacturerDao.update(entity);
    }

    @Override
    public Manufacturer getById(int id) {
        return manufacturerDao.getById(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }

    @Override
    public Long count() {
        return manufacturerDao.count();
    }
}

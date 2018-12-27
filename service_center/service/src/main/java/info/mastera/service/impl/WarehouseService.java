package info.mastera.service.impl;

import info.mastera.dao.IWarehouseDao;
import info.mastera.model.Warehouse;
import info.mastera.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseService implements IWarehouseService {

    @Autowired
    private IWarehouseDao<Warehouse> warehouseDao;

    @Override
    public Warehouse create(Warehouse entity) {
        return warehouseDao.create(entity);
    }

    @Override
    public void delete(Warehouse entity) {
        warehouseDao.delete(entity);
    }

    @Override
    public void update(Warehouse entity) {
        warehouseDao.update(entity);
    }

    @Override
    public Warehouse getById(int id) {
        return warehouseDao.getById(id);
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseDao.getAll();
    }

    @Override
    public Long count() {
        return warehouseDao.count();
    }

    @Override
    public List<Warehouse> getAllWithPart() {
        return warehouseDao.getAllWithPart();
    }

    @Override
    public Warehouse getByIdWithPart(int id) {
        return warehouseDao.getByIdWithPart(id);
    }

}

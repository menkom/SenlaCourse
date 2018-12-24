package info.mastera.dao.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.dao.IManufacturerDao;
import info.mastera.model.Manufacturer;
import org.springframework.stereotype.Repository;

@Repository
public class ManufacturerDao extends AbstractDao<Manufacturer> implements IManufacturerDao<Manufacturer> {
    @Override
    protected Class<Manufacturer> getTClass() {
        return Manufacturer.class;
    }
}

package info.mastera.dao;

import info.mastera.model.Warehouse;

import java.util.List;

public interface IWarehouseDao<T extends Warehouse> extends IGenericDao<T> {

    List<Warehouse> getAllAndPart();

}

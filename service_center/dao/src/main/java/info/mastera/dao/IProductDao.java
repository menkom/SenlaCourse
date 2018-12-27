package info.mastera.dao;

import info.mastera.model.Product;

import java.util.List;

public interface IProductDao<T extends Product> extends IGenericDao<T> {

    Product getByIdWithManufacturer(int id);

    List<Product> getAllWithManufacturer();

}

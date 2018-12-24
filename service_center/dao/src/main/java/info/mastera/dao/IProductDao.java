package info.mastera.dao;

import info.mastera.model.Product;

import java.util.List;

public interface IProductDao<T extends Product> extends IGenericDao<T> {

    List<Product> getAllAndManufacturer();

}

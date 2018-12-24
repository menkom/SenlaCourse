package info.mastera.service;

import info.mastera.model.Product;

import java.util.List;

public interface IProductService {

    Product create(Product entity);

    void delete(Product entity);

    void update(Product entity);

    Product getById(int id);

    List<Product> getAll();

    Long count();

}

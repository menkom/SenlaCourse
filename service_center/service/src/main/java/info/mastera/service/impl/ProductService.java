package info.mastera.service.impl;

import info.mastera.dao.IProductDao;
import info.mastera.model.Product;
import info.mastera.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private IProductDao<Product> productDao;

    @Override
    public Product create(Product entity) {
        return productDao.create(entity);
    }

    @Override
    public void delete(Product entity) {
        productDao.delete(entity);
    }

    @Override
    public void update(Product entity) {
        productDao.update(entity);
    }

    @Override
    public Product getById(int id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Long count() {
        return productDao.count();
    }

}

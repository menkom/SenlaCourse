package info.mastera.service.impl;

import info.mastera.dao.IPartCategoryDao;
import info.mastera.model.PartCategory;
import info.mastera.service.IPartCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartCategoryService implements IPartCategoryService {

    @Autowired
    private IPartCategoryDao<PartCategory> partCategoryDao;

    @Override
    public PartCategory create(PartCategory entity) {
        return partCategoryDao.create(entity);
    }

    @Override
    public void delete(PartCategory entity) {
        partCategoryDao.delete(entity);
    }

    @Override
    public void update(PartCategory entity) {
        partCategoryDao.update(entity);
    }

    @Override
    public PartCategory getById(int id) {
        return partCategoryDao.getById(id);
    }

    @Override
    public List<PartCategory> getAll() {
        return partCategoryDao.getAll();
    }

    @Override
    public Long count() {
        return partCategoryDao.count();
    }

}

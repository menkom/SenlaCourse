package info.mastera.service.impl;

import info.mastera.dao.IPartDao;
import info.mastera.model.Part;
import info.mastera.service.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartService implements IPartService {

    @Autowired
    private IPartDao<Part> partDao;

    @Override
    public Part create(Part entity) {
        return partDao.create(entity);
    }

    @Override
    public void delete(Part entity) {
        partDao.delete(entity);
    }

    @Override
    public void update(Part entity) {
        partDao.update(entity);
    }

    @Override
    public Part getById(int id) {
        return partDao.getById(id);
    }

    @Override
    public List<Part> getAll() {
        return partDao.getAll();
    }

    @Override
    public Long count() {
        return partDao.count();
    }

}
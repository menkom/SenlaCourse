package info.mastera.service.impl;

import info.mastera.dao.IServiceOrderJobDao;
import info.mastera.model.ServiceOrderJob;
import info.mastera.service.IServiceOrderJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceOrderJobService implements IServiceOrderJobService {

    @Autowired
    private IServiceOrderJobDao<ServiceOrderJob> serviceOrderJobDao;

    @Override
    public ServiceOrderJob create(ServiceOrderJob entity) {
        return serviceOrderJobDao.create(entity);
    }

    @Override
    public void delete(ServiceOrderJob entity) {
        serviceOrderJobDao.delete(entity);
    }

    @Override
    public void update(ServiceOrderJob entity) {
        serviceOrderJobDao.update(entity);
    }

    @Override
    public ServiceOrderJob getById(int id) {
        return serviceOrderJobDao.getById(id);
    }

    @Override
    public List<ServiceOrderJob> getAll() {
        return serviceOrderJobDao.getAll();
    }

    @Override
    public Long count() {
        return serviceOrderJobDao.count();
    }

}

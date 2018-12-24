package info.mastera.service.impl;

import info.mastera.dao.IServiceOrderPartDao;
import info.mastera.model.ServiceOrderPart;
import info.mastera.service.IServiceOrderPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceOrderPartService implements IServiceOrderPartService {

    @Autowired
    private IServiceOrderPartDao<ServiceOrderPart> serviceOrderPartDao;

    @Override
    public ServiceOrderPart create(ServiceOrderPart entity) {
        return serviceOrderPartDao.create(entity);
    }

    @Override
    public void delete(ServiceOrderPart entity) {
        serviceOrderPartDao.delete(entity);
    }

    @Override
    public void update(ServiceOrderPart entity) {
        serviceOrderPartDao.update(entity);
    }

    @Override
    public ServiceOrderPart getById(int id) {
        return serviceOrderPartDao.getById(id);
    }

    @Override
    public List<ServiceOrderPart> getAll() {
        return serviceOrderPartDao.getAll();
    }

    @Override
    public Long count() {
        return serviceOrderPartDao.count();
    }

}

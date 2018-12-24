package info.mastera.service.impl;

import info.mastera.dao.IServiceOrderDao;
import info.mastera.model.ServiceOrder;
import info.mastera.service.IServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceOrderService implements IServiceOrderService {

    @Autowired
    private IServiceOrderDao<ServiceOrder> serviceOrderDao;

    @Override
    public ServiceOrder create(ServiceOrder entity) {
        return serviceOrderDao.create(entity);
    }

    @Override
    public void delete(ServiceOrder entity) {
        serviceOrderDao.delete(entity);
    }

    @Override
    public void update(ServiceOrder entity) {
        serviceOrderDao.update(entity);
    }

    @Override
    public ServiceOrder getById(int id) {
        return serviceOrderDao.getById(id);
    }

    @Override
    public List<ServiceOrder> getAll() {
        return serviceOrderDao.getAll();
    }

    @Override
    public Long count() {
        return serviceOrderDao.count();
    }

}

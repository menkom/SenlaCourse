package info.mastera.service.impl;

import info.mastera.dao.IOrderJobDao;
import info.mastera.model.OrderJob;
import info.mastera.service.IOrderJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderJobService implements IOrderJobService {

    @Autowired
    private IOrderJobDao<OrderJob> orderJobDao;

    @Override
    public OrderJob create(OrderJob entity) {
        return orderJobDao.create(entity);
    }

    @Override
    public void delete(OrderJob entity) {
        orderJobDao.delete(entity);
    }

    @Override
    public void update(OrderJob entity) {
        orderJobDao.update(entity);
    }

    @Override
    public OrderJob getById(int id) {
        return orderJobDao.getById(id);
    }

    @Override
    public List<OrderJob> getAll() {
        return orderJobDao.getAll();
    }

    @Override
    public Long count() {
        return orderJobDao.count();
    }

}

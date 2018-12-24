package info.mastera.service;

import info.mastera.model.OrderJob;

import java.util.List;

public interface IOrderJobService {

    OrderJob create(OrderJob entity);

    void delete(OrderJob entity);

    void update(OrderJob entity);

    OrderJob getById(int id);

    List<OrderJob> getAll();

    Long count();

}

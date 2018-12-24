package info.mastera.service;

import info.mastera.model.ServiceOrder;

import java.util.List;

public interface IServiceOrderService {

    ServiceOrder create(ServiceOrder entity);

    void delete(ServiceOrder entity);

    void update(ServiceOrder entity);

    ServiceOrder getById(int id);

    List<ServiceOrder> getAll();

    Long count();

}

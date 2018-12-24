package info.mastera.service;

import info.mastera.model.ServiceOrderPart;

import java.util.List;

public interface IServiceOrderPartService {

    ServiceOrderPart create(ServiceOrderPart entity);

    void delete(ServiceOrderPart entity);

    void update(ServiceOrderPart entity);

    ServiceOrderPart getById(int id);

    List<ServiceOrderPart> getAll();

    Long count();


}

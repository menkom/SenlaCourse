package info.mastera.service;

import info.mastera.model.ServiceOrderJob;

import java.util.List;

public interface IServiceOrderJobService {

    ServiceOrderJob create(ServiceOrderJob entity);

    void delete(ServiceOrderJob entity);

    void update(ServiceOrderJob entity);

    ServiceOrderJob getById(int id);

    List<ServiceOrderJob> getAll();

    Long count();


}

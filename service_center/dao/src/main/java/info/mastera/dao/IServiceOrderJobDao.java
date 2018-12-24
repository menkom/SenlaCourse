package info.mastera.dao;

import info.mastera.model.ServiceOrderJob;

import java.util.List;

public interface IServiceOrderJobDao<T extends ServiceOrderJob> extends IGenericDao<T> {

    List<ServiceOrderJob> getAllAndUserOrderJob();

}

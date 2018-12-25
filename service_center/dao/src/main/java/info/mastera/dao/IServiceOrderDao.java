package info.mastera.dao;

import info.mastera.model.ServiceOrder;

import java.util.List;

public interface IServiceOrderDao<T extends ServiceOrder> extends IGenericDao<T> {

    List<ServiceOrder> getAllWithProductAndCustomer();

    ServiceOrder getByIdWithPartsAndJobs(int id);

}

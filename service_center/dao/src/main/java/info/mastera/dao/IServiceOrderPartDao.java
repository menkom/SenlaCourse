package info.mastera.dao;

import info.mastera.model.ServiceOrderJob;
import info.mastera.model.ServiceOrderPart;

import java.util.List;

public interface IServiceOrderPartDao<T extends ServiceOrderPart> extends IGenericDao<T> {

    List<ServiceOrderPart> getAllAndUserWarehouse();

}

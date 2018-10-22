package com.senla.hotel.dao;

import com.senla.hotel.dao.api.IServiceDao;
import com.senla.hotel.model.Service;

public class ServiceDao extends GenericDao<Service> implements IServiceDao<Service> {

	@Override
	protected Class<Service> getTClass() {
		return Service.class;
	}
}

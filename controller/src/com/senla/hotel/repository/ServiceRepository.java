package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Service;
import com.senla.util.IdGenerator;

public class ServiceRepository {

	private static ServiceRepository serviceRepository;

	private Integer lastId;
	private List<Service> services;

	private ServiceRepository() {
		super();
		this.services = new ArrayList<Service>();
	}

	public static ServiceRepository getInstance() {
		if (serviceRepository == null) {
			serviceRepository = new ServiceRepository();
		}
		return serviceRepository;
	}

	public List<Service> getServices() {
		return services;
	}

	public Boolean add(Service service) {
		Integer id = IdGenerator.generateId(lastId);
		service.setId(id);
		Boolean result = getServices().add(service);
		if (result) {
			lastId = id;
		}
		return result;
	}

	public void delete(Integer serviceCode) {
		for (int i = 0; i < getServices().size(); i++) {
			if (getServices().get(i).getCode() == serviceCode) {
				getServices().remove(i);
				break;
			}
		}
	}

	public Service getServiceByCode(int number) {
		for (Service service : getServices()) {
			if ((service != null) && (service.getCode() == number)) {
				return service;
			}
		}
		return null;
	}

	public Service getServiceById(Integer id) {
		for (Service service : getServices()) {
			if ((service != null) && (service.getId() == id)) {
				return service;
			}
		}
		return null;
	}

	public Integer getLastId() {
		return lastId;
	}

	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}

}

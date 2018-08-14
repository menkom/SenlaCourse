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

	public Boolean delete(Integer serviceCode) {
		Boolean result = false;
		for (int i = 0; i < getServices().size(); i++) {
			if (getServices().get(i).getCode().equals(serviceCode)) {
				getServices().remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Boolean deleteById(Integer id) {
		Boolean result = false;
		for (int i = 0; i < services.size() - 1; i++) {
			if (services.get(i).getId().equals(id)) {
				services.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Service getServiceByCode(Integer number) {
		for (Service service : getServices()) {
			if ((service != null) && (service.getCode().equals(number))) {
				return service;
			}
		}
		return null;
	}

	public Service getServiceById(Integer id) {
		for (Service service : getServices()) {
			if ((service != null) && (service.getId().equals(id))) {
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

	public Boolean update(Service service) {
		Boolean result = false;
		if (service != null) {
			for (int i = 0; i < getServices().size(); i++) {
				if ((getServices().get(i) != null) && (getServices().get(i).getId().equals(service.getId()))) {
					getServices().set(i, service);
				}
			}
		}
		result = true;
		return result;
	}

}

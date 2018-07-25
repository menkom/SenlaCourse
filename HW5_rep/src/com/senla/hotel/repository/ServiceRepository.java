package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Service;

public class ServiceRepository {

	private List<Service> services;

	private static ServiceRepository serviceRepository;

	private ServiceRepository() {
		super();
		services = new ArrayList<>();
	}

	public List<Service> getServices() {
		return services;
	}

	public void add(Service element) {
		getServices().add(element);
	}

	public void delete(Service element) {
		for (Service service : getServices()) {
			if (service == element) {
				service = null;
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

	public static ServiceRepository getInstance() {
		if (serviceRepository == null) {
			serviceRepository = new ServiceRepository();
		}
		return serviceRepository;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}

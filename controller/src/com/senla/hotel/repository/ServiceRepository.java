package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Service;

public class ServiceRepository {

	private static ServiceRepository serviceRepository;

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

	public void add(Service service) {
		getServices().add(service);
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

}

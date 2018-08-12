package com.senla.hotel.services;

import java.util.Comparator;
import java.util.List;

import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ServiceRepository;

public class ServiceService implements IService {

	private static ServiceService serviceService;

	private ServiceRepository serviceRepository;

	private ServiceService() {
		super();
		this.serviceRepository = ServiceRepository.getInstance();
	}

	public static ServiceService getInstance() {
		if (serviceService == null) {
			serviceService = new ServiceService();
		}
		return serviceService;
	}

	public ServiceRepository getServiceRepository() {
		return serviceRepository;
	}

	public void add(Service service) {
		getServiceRepository().add(service);
	}

	public Boolean addService(int code, String name, int price) {
		Service service = new Service(code, name, price);
		return getServiceRepository().add(service);
	}

	public List<Service> getAllServices(Comparator<Service> comparator) {
		List<Service> result = getServiceRepository().getServices();
		result.sort(comparator);
		return result;
	}

	public Service getServiceByCode(Integer code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public Boolean changeServicePrice(Integer code, Integer price) {
		Boolean result = false;
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
			result = true;
		}
		return result;
	}

}

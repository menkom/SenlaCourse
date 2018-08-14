package com.senla.hotel.services;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.senla.exception.NoEntryException;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.util.ExportCSV;

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

	public Boolean add(Service service) {
		return getServiceRepository().add(service);
	}

	public Boolean addService(int code, String name, int price) {
		Service service = new Service(code, name, price);
		return add(service);
	}

	public Boolean update(Service service) {
		return getServiceRepository().update(service);
	}

	public List<Service> getAllServices(Comparator<Service> comparator) {
		List<Service> result = getServiceRepository().getServices();
		result.sort(comparator);
		return result;
	}

	public Service getServiceByCode(Integer code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public Service getServiceById(int id) {
		return getServiceRepository().getServiceById(id);
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

	public Boolean exportServiceCSV(Integer code) throws NoEntryException, IOException {
		Service service = getServiceByCode(code);
		if (service == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(service.toString(), "service_" + service.getId() + ".csv");
		}
	}

	public Boolean importServicesCSV(String file) throws NoEntryException, IOException {
		Boolean result = false;
		List<Service> rooms = ExportCSV.getServicesFromCSV(file);
		for (Service room : rooms) {
			if (getServiceById(room.getId()) != null) {
				update(room);
			} else {
				add(room);
			}
		}
		result = true;
		return result;
	}

}

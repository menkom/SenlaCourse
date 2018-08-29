package com.senla.hotel.services;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.api.IServiceRepository;
import com.senla.hotel.services.api.IServiceService;
import com.senla.util.ExportCSV;

public class ServiceService implements IServiceService {

	private static IServiceService serviceService;

	private IServiceRepository serviceRepository;

	public ServiceService() {
		super();
		this.serviceRepository = (IServiceRepository) DependencyInjection.getInstance()
				.getInterfacePair(IServiceRepository.class);
	}

	public static IServiceService getInstance() {
		if (serviceService == null) {
			serviceService = (IServiceService) DependencyInjection.getInstance()
					.getInterfacePair(IServiceService.class);
		}
		return serviceService;
	}

	public IServiceRepository getServiceRepository() {
		return serviceRepository;
	}

	public boolean add(Service service) {
		return getServiceRepository().add(service);
	}

	public boolean addService(int code, String name, int price) {
		Service service = new Service(code, name, price);
		return add(service);
	}

	public boolean update(Service service) {
		return getServiceRepository().update(service);
	}

	public List<Service> getAllServices(Comparator<Service> comparator) {
		List<Service> result = getServiceRepository().getServices();
		result.sort(comparator);
		return result;
	}

	public Service getServiceByCode(int code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public Service getServiceById(int id) {
		return getServiceRepository().getServiceById(id);
	}

	public boolean changeServicePrice(int code, int price) {
		boolean result = false;
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
			result = true;
		}
		return result;
	}

	public boolean exportServiceCSV(int code, String fileName) throws IOException {
		Service service = getServiceByCode(code);
		if (service == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(service.toString(), fileName);
		}
	}

	public boolean importServicesCSV(String file) throws IOException {
		boolean result = false;
		List<Service> rooms = ExportCSV.getServicesFromCSV(file);
		for (Service room : rooms) {
			if (getServiceById(room.getId()) != null) {
				result = update(room);
			} else {
				result = add(room);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	public boolean exportCsv(String csvFilePath) {
		return getServiceRepository().exportCsv(csvFilePath);
	}

	public boolean importCsv(String csvFilePath) {
		return getServiceRepository().importCsv(csvFilePath);
	}

}

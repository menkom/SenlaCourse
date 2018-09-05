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
		this.serviceRepository = DependencyInjection.getInstance().getInterfacePair(IServiceRepository.class);
	}

	public static IServiceService getInstance() {
		if (serviceService == null) {
			serviceService = DependencyInjection.getInstance().getInterfacePair(IServiceService.class);
		}
		return serviceService;
	}

	@Override
	public boolean add(Service service) {
		return serviceRepository.add(service);
	}

	@Override
	public boolean addAll(List<Service> services) {
		return serviceRepository.addAll(services);
	}

	@Override
	public boolean addService(int code, String name, int price) {
		Service service = new Service(code, name, price);
		return add(service);
	}

	@Override
	public boolean update(Service service) {
		return serviceRepository.update(service);
	}

	@Override
	public List<Service> getServices() {
		return serviceRepository.getServices();
	}

	@Override
	public List<Service> getAllServices(Comparator<Service> comparator) {
		List<Service> result = getServices();
		result.sort(comparator);
		return result;
	}

	@Override
	public Service getServiceByCode(int code) {
		return serviceRepository.getServiceByCode(code);
	}

	@Override
	public Service getServiceById(int id) {
		return serviceRepository.getServiceById(id);
	}

	@Override
	public boolean changeServicePrice(int code, int price) {
		boolean result = false;
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
			result = true;
		}
		return result;
	}

	@Override
	public boolean exportServiceCSV(int code, String fileName) throws IOException {
		Service service = getServiceByCode(code);
		if (service == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(service.toString(), fileName);
		}
	}

	@Override
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

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		return serviceRepository.exportCsv(csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		return serviceRepository.importCsv(csvFilePath);
	}

}

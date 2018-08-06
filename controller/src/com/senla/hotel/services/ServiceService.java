package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
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

	public Service getServiceByCode(int code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public Boolean loadFromFile(String filePath) throws IOException, NumberFormatException, ParseException {
		Boolean result = false;
		String[] array = new TextFileWorker(filePath + "service.db").readFromFile();

		result = serviceRepository.getServices().addAll(ListConverter.getServices(array));
		return result;
	}

	public Boolean saveToFile(String filePath) {
		Boolean result = false;
		new TextFileWorker(filePath + "service.db")
				.writeToFile(ListConverter.getArrayFromList(serviceRepository.getServices()));
		result = true;
		return result;
	}

	public Boolean changeServicePrice(int code, int price) {
		Boolean result = false;
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
			result = true;
		}
		return result;
	}

}

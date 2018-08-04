package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ServiceRepository;

public class ServiceService implements IService {

	private ServiceRepository serviceRepository;
	private static ServiceService serviceService;

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

	public void addService(int code, String name, int price) {
		Service service = new Service(code, name, price);
		getServiceRepository().add(service);
	}

	public List<Service> getAllServices() {
		return getServiceRepository().getServices();
	}

	public Service getServiceByCode(int code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public void loadFromFile(String filePath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(filePath + "service.db").readFromFile();

		serviceRepository.getServices().addAll(ListConverter.getServices(array));
	}

	public void saveToFile(String filePath) throws IOException {
		new TextFileWorker(filePath + "service.db")
				.writeToFile(ListConverter.getArrayFromList(serviceRepository.getServices()));
	}

	public void changeServicePrice(int code, int price) {
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
		}
	}

}

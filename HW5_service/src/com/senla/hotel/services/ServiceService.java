package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ServiceRepository;
//import com.senla.util.FileOperator;

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

	public String getFileName() {
		return "service.db";
	}

	public ServiceRepository getServiceRepository() {
		return serviceRepository;
	}

	public void add(Service element) {
		getServiceRepository().add(element);
	}

	public void addService(int code, String name, int price) {
		Service element = new Service(code, name, price);
		getServiceRepository().add(element);
	}

	public List<Service> getAllServices() {
		return getServiceRepository().getServices();
	}

	public Service getServiceByCode(int code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(dbPath + getFileName()).readFromFile();

		serviceRepository.setServices(ListConverter.getServices(array));
	}

	public void saveToDB(String dbPath) throws IOException {
		new TextFileWorker(dbPath + getFileName())
				.writeToFile(ListConverter.getArrayFromList(serviceRepository.getServices()));
	}

	public String[] getStringToSave() {
		return null;// getServiceRepository().toStringArray();
	}

	public void changeServicePrice(int code, int price) {
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
		}
	}

}

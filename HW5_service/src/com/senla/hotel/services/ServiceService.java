package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
		// ServiceService service = new FileOperator().getServiceService(dbPath +
		// getFileName(), getClientRepository(),
		// getRoomRepository(), getServiceRepository(), getOrderRepository());
		//
		// getRepository().setRepository(((ServiceRepository)
		// service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) throws IOException {
//		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

	@Override
	public String[] getStringToSave() {
		return getServiceRepository().toStringArray();
	}

	public void changeServicePrice(int code, int price) {
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
		}
	}

}

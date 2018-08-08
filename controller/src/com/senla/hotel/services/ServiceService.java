package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.util.Serialization;

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

		serviceRepository.setLastId(Integer.parseInt(array[0]));

		result = serviceRepository.getServices()
				.addAll(ListConverter.getServices(Arrays.copyOfRange(array, 1, array.length)));
		return result;
	}

	public Boolean loadFromRaw(String filePath)
			throws IOException, NumberFormatException, ParseException, ClassNotFoundException {
		Boolean result = false;
		ServiceRepository services = Serialization.deserialize(filePath + "service.raw");

		if (services != null) {
			serviceRepository.setLastId(services.getLastId());

			result = serviceRepository.getServices().addAll(services.getServices());
		}
		return result;
	}

	public Boolean saveToFile(String filePath) {
		Boolean result = false;
		String[] repositoryToStr = ListConverter.getArrayFromList(serviceRepository.getServices());
		String[] array = new String[repositoryToStr.length + 1];
		array[0] = Integer.toString(serviceRepository.getLastId());
		System.arraycopy(repositoryToStr, 0, array, 1, repositoryToStr.length);

		new TextFileWorker(filePath + "service.db").writeToFile(array);
		result = true;
		return result;
	}

	public Boolean saveToRaw(String filePath) throws IOException {
		Boolean result = false;
		result = Serialization.serialize(getServiceRepository(), filePath + "service.raw");
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

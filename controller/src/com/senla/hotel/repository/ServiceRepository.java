package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.annotation.parser.CsvParser;
import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.api.IServiceRepository;
import com.senla.util.IdGenerator;

public class ServiceRepository implements IServiceRepository {

	private static IServiceRepository serviceRepository;

	private static Integer lastId;
	private List<Service> services;

	public ServiceRepository() {
		super();
		this.services = new ArrayList<Service>();
	}

	public static IServiceRepository getInstance() {
		if (serviceRepository == null) {
			serviceRepository = DependencyInjection.getInstance().getInterfacePair(IServiceRepository.class);
		}
		return serviceRepository;
	}

	public List<Service> getServices() {
		return services;
	}

	public boolean add(Service service) {
		boolean result = false;
		if (service.getId() != null) {
			result = services.add(service);
		} else {
			Integer id = IdGenerator.generateId(getLastId());
			service.setId(id);
			result = getServices().add(service);
			if (result) {
				setLastId(id);
			}
		}
		return result;
	}

	public boolean addAll(List<Service> services) {
		boolean result = getServices().addAll(services);
		if (result) {
			setLastId(IdGenerator.getLastId(getServices()));
		}
		return result;
	}

	public boolean delete(Integer serviceCode) {
		boolean result = false;
		for (int i = 0; i < getServices().size(); i++) {
			if (getServices().get(i).getCode().equals(serviceCode)) {
				getServices().remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean deleteById(Integer id) {
		boolean result = false;
		for (int i = 0; i < services.size() - 1; i++) {
			if (services.get(i).getId().equals(id)) {
				services.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Service getServiceByCode(Integer number) {
		for (Service service : getServices()) {
			if ((service != null) && (service.getCode().equals(number))) {
				return service;
			}
		}
		return null;
	}

	public Service getServiceById(Integer id) {
		for (Service service : getServices()) {
			if ((service != null) && (service.getId().equals(id))) {
				return service;
			}
		}
		return null;
	}

	private static Integer getLastId() {
		return ServiceRepository.lastId;
	}

	private static void setLastId(Integer lastId) {
		ServiceRepository.lastId = lastId;
	}

	public boolean update(Service service) {
		boolean result = false;
		if (service != null) {
			for (int i = 0; i < getServices().size(); i++) {
				if ((getServices().get(i) != null) && (getServices().get(i).getId().equals(service.getId()))) {
					getServices().set(i, service);
				}
			}
		}
		result = true;
		return result;
	}

	public boolean exportCsv(String csvFilePath) {
		return CsvParser.exportToCsv(getServices(), csvFilePath);
	}

	public boolean importCsv(String csvFilePath) {
		return addAll(CsvParser.importFromCsv(Service.class, csvFilePath));
	}

}

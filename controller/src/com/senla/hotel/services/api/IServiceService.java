package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.senla.hotel.model.Service;

public interface IServiceService {

	boolean add(Service service);

	boolean addAll(List<Service> services);

	boolean addService(int code, String name, int price);

	boolean update(Service service);

	public List<Service> getServices();

	List<Service> getAllServices(Comparator<Service> comparator);

	Service getServiceByCode(int code);

	Service getServiceById(int id);

	boolean changeServicePrice(int code, int price);

	boolean exportServiceCSV(int code, String fileName) throws IOException;

	boolean importServicesCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath);

	boolean importCsv(String csvFilePath);

}

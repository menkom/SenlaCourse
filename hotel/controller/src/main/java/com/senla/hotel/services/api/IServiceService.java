package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.List;

import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.model.Service;

public interface IServiceService {

	void add(Service service);

	void addAll(List<Service> services);

	void addService(Service service);

	void update(Service service);

	List<Service> getServices(EnumServiceSort serviceSort);

	Service getServiceById(int id);

	void changeServicePrice(int serviceId, int price);

	boolean exportServiceCSV(int serviceId, String fileName) throws IOException;

	boolean importServicesCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;

}

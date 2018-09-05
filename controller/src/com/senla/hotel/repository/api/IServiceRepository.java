package com.senla.hotel.repository.api;

import java.io.IOException;
import java.util.List;

import com.senla.hotel.model.Service;

public interface IServiceRepository {

	List<Service> getServices();

	boolean add(Service service);

	boolean addAll(List<Service> services);

	boolean delete(Integer serviceCode);

	boolean deleteById(Integer id);

	Service getServiceByCode(Integer number);

	Service getServiceById(Integer id);

	boolean update(Service service);

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;
}

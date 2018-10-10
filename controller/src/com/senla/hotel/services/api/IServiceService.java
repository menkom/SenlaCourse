package com.senla.hotel.services.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.model.Service;

public interface IServiceService {

	boolean add(Service service) throws SQLException;

	boolean addAll(List<Service> services) throws SQLException;

	boolean addService(int code, String name, int price) throws SQLException;

	boolean update(Service service) throws SQLException;

	public List<Service> getServices() throws SQLException;

	List<Service> getAllServices(EnumServiceSort serviceSort) throws SQLException;

	Service getServiceById(int id) throws SQLException;

	boolean changeServicePrice(int serviceId, int price) throws SQLException;

	boolean exportServiceCSV(int serviceId, String fileName) throws IOException, SQLException;

	boolean importServicesCSV(String file) throws IOException, SQLException;

	boolean exportCsv(String csvFilePath) throws IOException, SQLException;

	boolean importCsv(String csvFilePath) throws IOException, SQLException;

}

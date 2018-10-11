package com.senla.hotel.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IServiceDao;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.model.Service;
import com.senla.hotel.services.api.IServiceService;
import com.senla.util.ExportCSV;

public class ServiceService implements IServiceService {

	private static IServiceService serviceService;

	private DbConnector dbConnector;

	private IServiceDao<Service> serviceDao;

	@SuppressWarnings("unchecked")
	public ServiceService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.serviceDao = DependencyInjection.getInstance().getInterfacePair(IServiceDao.class);
	}

	public static IServiceService getInstance() {
		if (serviceService == null) {
			serviceService = DependencyInjection.getInstance().getInterfacePair(IServiceService.class);
		}
		return serviceService;
	}

	@Override
	public boolean add(Service service) throws SQLException {
		return serviceDao.add(dbConnector.getConnection(), service);
	}

	@Override
	public boolean addAll(List<Service> services) throws SQLException {
		return serviceDao.addAll(dbConnector.getConnection(), services);
	}

	@Override
	public boolean addService(int serviceId, String name, int price) throws SQLException {
		Service service = new Service(serviceId, name, price);
		return add(service);
	}

	@Override
	public boolean update(Service service) throws SQLException {
		return serviceDao.update(dbConnector.getConnection(), service);
	}

	@Override
	public List<Service> getServices() throws SQLException {
		return serviceDao.getAll(dbConnector.getConnection(), "");
	}

	@Override
	public List<Service> getAllServices(EnumServiceSort serviceSort) throws SQLException {
		return serviceDao.getAll(dbConnector.getConnection(), serviceSort.getTableField());
	}

	@Override
	public Service getServiceById(int serviceId) throws SQLException {
		return serviceDao.getById(dbConnector.getConnection(), serviceId);
	}

	@Override
	public boolean changeServicePrice(int serviceId, int price) throws SQLException {
		boolean result = false;
		Service service = getServiceById(serviceId);
		if (service != null) {
			service.setPrice(price);
			result = true;
		}
		return result;
	}

	@Override
	public boolean exportServiceCSV(int serviceId, String fileName) throws IOException, SQLException {
		Service service = getServiceById(serviceId);
		if (service == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(service.toString(), fileName);
		}
	}

	@Override
	public boolean importServicesCSV(String file) throws IOException, SQLException {
		boolean result = false;
		List<Service> rooms = ExportCSV.getServicesFromCSV(file);
		for (Service room : rooms) {
			if (getServiceById(room.getId()) != null) {
				result = update(room);
			} else {
				result = add(room);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException, SQLException {
		return serviceDao.exportCsv(dbConnector.getConnection(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException, SQLException {
		return serviceDao.importCsv(dbConnector.getConnection(), csvFilePath);
	}

}

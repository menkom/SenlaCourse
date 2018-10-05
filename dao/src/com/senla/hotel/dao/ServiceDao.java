package com.senla.hotel.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.senla.annotation.parser.CsvParser;
import com.senla.dao.DaoHandler;
import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.api.IServiceRepository;

public class ServiceDao implements IServiceRepository {

	private static final Logger logger = Logger.getLogger(ServiceDao.class);

	private static IServiceRepository serviceRepository;

	private ServiceDao() {
		super();
	}

	public static IServiceRepository getInstance() {
		if (serviceRepository == null) {
			serviceRepository = DependencyInjection.getInstance().getInterfacePair(IServiceRepository.class);
		}
		return serviceRepository;
	}

	@Override
	public List<Service> getServices() {
		List<Service> resultList = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from service");

			while (resultSet.next()) {
				Service service = parseResultSet(resultSet);
				resultList.add(service);
			}

		} catch (SQLException e) {
			logger.error(e);
		}
		return resultList;
	}

	@Override
	public boolean add(Service service) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate(
					"insert into service (service_id, service_code, service_name, service_price) values ("
							+ service.getId() + ", " + service.getCode() + ", \'" + service.getName() + "\', "
							+ service.getPrice() + ")");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean addAll(List<Service> services) {
		int result = 0;
		if (services.size() > 0) {
			StringBuilder query = new StringBuilder(
					"insert into service (service_id, service_code, service_name, service_price) values ");
			for (Service service : services) {
				query.append("(" + service.getId() + ", \'" + service.getCode() + "\', \'" + service.getName() + "\', "
						+ service.getPrice() + "),");
			}
			query.deleteCharAt(query.length() - 1);
			try {
				result = DaoHandler.getInstance().executeUpdate(query.toString());
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return result > 0;
	}

	@Override
	public boolean delete(Integer serviceCode) {
		int result = 0;
		try {
			result = DaoHandler.getInstance()
					.executeUpdate("delete from service where service_code=\'" + serviceCode + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from service where service_id=\'" + id + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public Service getServiceByCode(Integer code) {
		Service service = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance()
					.executeQuery("select * from service where service_code=\'" + code + "\'");

			while (resultSet.next()) {
				service = parseResultSet(resultSet);
				return service;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return service;
	}

	@Override
	public Service getServiceById(Integer id) {
		Service service = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from service where service_id=\'" + id + "\'");

			while (resultSet.next()) {
				service = parseResultSet(resultSet);
				return service;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return service;
	}

	@Override
	public boolean update(Service service) {
		int result = 0;
		try {
			result = DaoHandler.getInstance()
					.executeUpdate("update service set service_code=" + service.getCode() + ", service_name=\'"
							+ service.getName() + "\', service_price=" + service.getPrice() + " where service_id=\'"
							+ service.getId() + "\'");

		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		return CsvParser.exportToCsv(getServices(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		return addAll(CsvParser.importFromCsv(Service.class, csvFilePath));
	}

	public Service parseResultSet(ResultSet resultSet) throws SQLException {
		Service service = new Service();
		service.setId(resultSet.getInt("service_id"));
		service.setCode(resultSet.getInt("service_code"));
		service.setName(resultSet.getString("service_name"));
		service.setPrice(resultSet.getInt("service_price"));
		return service;

	}
}

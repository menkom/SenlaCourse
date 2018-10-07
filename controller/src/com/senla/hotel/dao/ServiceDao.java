package com.senla.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.senla.hotel.dao.api.IServiceDao;
import com.senla.hotel.model.Service;

public class ServiceDao extends GenericDao<Service> implements IServiceDao<Service> {

	private static final String INSERT_ENTITY = "insert into service (service_code, service_name, service_price)"
			+ " values (?, ?, ?)";
	private static final String UPDATE_ENTITY = "update service set service_code=?, service_name=?, service_price=?"
			+ " where service_id=?";

	@Override
	public Service parseResultSet(ResultSet resultSet) throws SQLException {
		Service service = new Service();
		service.setId(resultSet.getInt("service_id"));
		service.setCode(resultSet.getInt("service_code"));
		service.setName(resultSet.getString("service_name"));
		service.setPrice(resultSet.getInt("service_price"));
		return service;
	}

	@Override
	protected String getTableName() {
		return "service";
	}

	@Override
	protected String getIdColumn() {
		return "service_id";
	}

	@Override
	public int add(Connection connection, Service entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_ENTITY)) {
			ps.setInt(1, entity.getCode());
			ps.setString(2, entity.getName());
			ps.setInt(3, entity.getPrice());
			System.out.println(ps);
			return ps.executeUpdate();
		}
	}

	@Override
	public int update(Connection connection, Service entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_ENTITY)) {
			ps.setInt(1, entity.getCode());
			ps.setString(2, entity.getName());
			ps.setInt(3, entity.getPrice());
			ps.setInt(4, entity.getId());
			System.out.println(ps);
			return ps.executeUpdate();
		}
	}

}

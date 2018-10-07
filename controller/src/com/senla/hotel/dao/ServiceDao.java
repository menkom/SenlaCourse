package com.senla.hotel.dao;

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
	protected void prepareAddStatement(PreparedStatement ps, Service entity) throws SQLException {
		ps.setInt(1, entity.getCode());
		ps.setString(2, entity.getName());
		ps.setInt(3, entity.getPrice());
		System.out.println(ps);
	}

	@Override
	protected String getInsertQuery() {
		return INSERT_ENTITY;
	}

	@Override
	protected Class<Service> getTClass() {
		return Service.class;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_ENTITY;
	}
}

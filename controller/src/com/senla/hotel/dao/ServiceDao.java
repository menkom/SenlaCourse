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
	private static final String TABLE_NAME = "service";
	private static final String TABLE_COLUMN_ID = "service_id";
	private static final String TABLE_COLUMN_CODE = "service_code";
	private static final String TABLE_COLUMN_NAME = "service_name";
	private static final String TABLE_COLUMN_PRICE = "service_price";

	@Override
	public Service parseResultSet(ResultSet resultSet) throws SQLException {
		Service service = new Service();
		service.setId(resultSet.getInt(TABLE_COLUMN_ID));
		service.setCode(resultSet.getInt(TABLE_COLUMN_CODE));
		service.setName(resultSet.getString(TABLE_COLUMN_NAME));
		service.setPrice(resultSet.getInt(TABLE_COLUMN_PRICE));
		return service;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected String getIdColumn() {
		return TABLE_COLUMN_ID;
	}

	@Override
	protected void prepareAddStatement(PreparedStatement ps, Service entity) throws SQLException {
		ps.setInt(1, entity.getCode());
		ps.setString(2, entity.getName());
		ps.setInt(3, entity.getPrice());
		ps.setInt(4, entity.getId());
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

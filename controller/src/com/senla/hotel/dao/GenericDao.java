package com.senla.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senla.base.BaseObject;
import com.senla.hotel.dao.api.IGenericDao;

public abstract class GenericDao<T extends BaseObject> implements IGenericDao<T> {

	private static final String SELECT_ALL = "SELECT * FROM `%s` order by (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM `%s` where %s =?";
	private static final String DELETE_BY_ID = "DELETE FROM `%s` where %s =?";

	public abstract T parseResultSet(ResultSet resultSet) throws SQLException;

	protected abstract String getTableName();

	protected abstract String getIdColumn();

	protected String getAllQuery() {
		return SELECT_ALL;
	}

	protected String getByIdQuery() {
		return SELECT_BY_ID;
	}

	public abstract int add(Connection connection, T entity) throws SQLException;

	public abstract int update(Connection connection, T entity) throws SQLException;

	public List<T> getAll(Connection connection, String sortColumn) throws SQLException {
		List<T> result = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(getAllQuery(), getTableName()))) {
			ps.setString(1, (sortColumn.equals("") ? getIdColumn() : sortColumn));
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				T entity = parseResultSet(resultSet);
				result.add(entity);
			}
		}
		return result;
	}

	public T getById(Connection connection, int id) throws SQLException {
		try (PreparedStatement ps = connection
				.prepareStatement(String.format(getByIdQuery(), getTableName(), getIdColumn()))) {
			ps.setInt(1, id);
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				return parseResultSet(resultSet);
			}
		}
		return null;
	}

	public int delete(Connection connection, int id) throws SQLException {
		try (PreparedStatement ps = connection
				.prepareStatement(String.format(DELETE_BY_ID, getTableName(), getIdColumn()))) {
			ps.setInt(1, id);
			System.out.println(ps);
			return ps.executeUpdate();
		}
	}

	protected boolean isExist(ResultSet resultSet, String columnName) throws SQLException {
		if (columnName == null || (columnName = columnName.trim()).isEmpty()) {
			return false;
		}
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			if (columnName.equals(metaData.getColumnName(i))) {
				return true;
			}
		}

		return false;
	}

}

package com.senla.hotel.dao.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.senla.base.BaseObject;

public interface IGenericDao<T extends BaseObject> {

	int add(Connection connection, T entity) throws SQLException;

	int update(Connection connection, T entity) throws SQLException;

	List<T> getAll(Connection connection, String sortColumn) throws SQLException;

	T getById(Connection connection, int id) throws SQLException;

	int delete(Connection connection, int id) throws SQLException;

	T parseResultSet(ResultSet resultSet) throws SQLException;
}

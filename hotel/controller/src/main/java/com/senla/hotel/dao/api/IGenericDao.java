package com.senla.hotel.dao.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.senla.base.BaseObject;

public interface IGenericDao<T extends BaseObject> {

	boolean add(Connection connection, T entity) throws SQLException;

	boolean update(Connection connection, T entity) throws SQLException;

	List<T> getAll(Connection connection, String sortColumn) throws SQLException;

	boolean addAll(Connection connection, List<T> list) throws SQLException;

	T getById(Connection connection, int id) throws SQLException;

	boolean delete(Connection connection, int id) throws SQLException;

	T parseResultSet(ResultSet resultSet) throws SQLException;

	boolean exportCsv(Connection connection, String csvFilePath) throws IOException, SQLException;

	boolean importCsv(Connection connection, String csvFilePath) throws IOException, SQLException;

}

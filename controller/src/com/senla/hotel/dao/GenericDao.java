package com.senla.hotel.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.annotation.parser.CsvParser;
import com.senla.base.BaseObject;
import com.senla.hotel.dao.api.IGenericDao;

public abstract class GenericDao<T extends BaseObject> implements IGenericDao<T> {

	private static final Logger logger = Logger.getLogger(GenericDao.class);

	private static final String SELECT_ALL = "SELECT * FROM `%s` order by (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM `%s` where %s =?";
	private static final String DELETE_BY_ID = "DELETE FROM `%s` where %s =?";

	@Override
	public abstract T parseResultSet(ResultSet resultSet) throws SQLException;

	protected abstract String getTableName();

	protected abstract String getIdColumn();

	protected abstract void prepareAddStatement(PreparedStatement ps, T entity) throws SQLException;

	protected abstract String getInsertQuery();

	protected abstract String getUpdateQuery();

	protected abstract Class<T> getTClass();

	protected String getAllQuery() {
		return SELECT_ALL;
	}

	protected String getByIdQuery() {
		return SELECT_BY_ID;
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

	@Override
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

	@Override
	public boolean add(Connection connection, T entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
			prepareAddStatement(ps, entity);
			int result = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int lastInsertedId = rs.getInt(1);
				entity.setId(lastInsertedId);
			}
			return result > 0;
		}
	}

	@Override
	public boolean addAll(Connection connection, List<T> list) throws SQLException {
		boolean result = false;
		connection.setAutoCommit(false);
		try (PreparedStatement ps = connection.prepareStatement(getInsertQuery())) {
			for (T entity : list) {
				prepareAddStatement(ps, entity);
				ps.addBatch();
			}
			int[] batchResult = ps.executeBatch();
			for (int i : batchResult) {
				if (i == 0) {
					break;
				}
			}
			connection.setAutoCommit(true);
			result = true;
		} catch (SQLException e) {
			logger.error(e);
			connection.rollback();
			throw e;
		}
		return result;
	}

	@Override
	public boolean update(Connection connection, T entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
			prepareAddStatement(ps, entity);
			ps.setInt(6, entity.getId());
			System.out.println(ps);
			return ps.executeUpdate() > 0;
		}
	}

	@Override
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

	@Override
	public boolean delete(Connection connection, int id) throws SQLException {
		try (PreparedStatement ps = connection
				.prepareStatement(String.format(DELETE_BY_ID, getTableName(), getIdColumn()))) {
			ps.setInt(1, id);
			System.out.println(ps);
			return ps.executeUpdate() > 0;
		}
	}

	@Override
	public boolean exportCsv(Connection connection, String csvFilePath) throws IOException, SQLException {
		return CsvParser.exportToCsv(getAll(connection, ""), csvFilePath);
	}

	@Override
	public boolean importCsv(Connection connection, String csvFilePath) throws IOException, SQLException {
		return addAll(connection, CsvParser.importFromCsv(getTClass(), csvFilePath));
	}

}

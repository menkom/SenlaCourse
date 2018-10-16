package com.senla.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.model.Client;

public class ClientDao extends GenericDao<Client> implements IClientDao<Client> {

	private static final String INSERT_ENTITY = "insert into client (client_name) values (?)";
	private static final String UPDATE_ENTITY = "update client set client_name=? where client_id=?";
	private static final String TABLE_COLUMN_ID = "client_id";
	private static final String TABLE_COLUMN_NAME = "client_name";
	private static final String TABLE_NAME = "client";
	private static final String SELECT_COUNT_CLIENTS = "SELECT count(client_id) count FROM `client`";
	private static final String TABLE_COLUMN_COUNT = "count";

	@Override
	public Client parseResultSet(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setId(resultSet.getInt(TABLE_COLUMN_ID));
		client.setName(resultSet.getString(TABLE_COLUMN_NAME));
		return client;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected String getIdColumn() {
		return TABLE_COLUMN_ID;
	}

	protected void prepareAddStatement(PreparedStatement ps, Client entity) throws SQLException {
		ps.setString(1, entity.getName());
		ps.setInt(2, entity.getId());
	}

	@Override
	protected String getInsertQuery() {
		return INSERT_ENTITY;
	}

	@Override
	protected Class<Client> getTClass() {
		return Client.class;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_ENTITY;
	}
	
	@Override
	public int getNumberOfClients(Connection connection) throws SQLException {
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_COUNT_CLIENTS)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(TABLE_COLUMN_COUNT);
			}
		}
		return result;
	}

}

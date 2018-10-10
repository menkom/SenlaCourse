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

	@Override
	public Client parseResultSet(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setId(resultSet.getInt("client_id"));
		client.setName(resultSet.getString("client_name"));
		return client;
	}

	@Override
	protected String getTableName() {
		return "client";
	}

	@Override
	protected String getIdColumn() {
		return "client_id";
	}

	@Override
	public boolean update(Connection connection, Client entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_ENTITY)) {
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getId());
			return ps.executeUpdate() > 0;
		}
	}

	protected void prepareAddStatement(PreparedStatement ps, Client entity) throws SQLException {
		ps.setString(1, entity.getName());
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

}

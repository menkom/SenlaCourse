package com.senla.hotel.dao.api;

import java.sql.Connection;
import java.sql.SQLException;

import com.senla.hotel.model.Client;

public interface IClientDao<T extends Client> extends IGenericDao<T> {

	int getNumberOfClients(Connection connection) throws SQLException;

}
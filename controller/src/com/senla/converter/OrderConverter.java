package com.senla.converter;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.dao.api.IServiceDao;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class OrderConverter {

	private static final Logger logger = Logger.getLogger(OrderConverter.class);
	public static final String SEPARATOR = ";";

	public static Order getOrderFromString(String line) throws ClassNotFoundException {

		String[] array = line.split(SEPARATOR);

		@SuppressWarnings("unchecked")
		IClientDao<Client> clientDao = DependencyInjection.getInstance().getInterfacePair(IClientDao.class);
		Client client = null;
		try {
			client = clientDao.getById(DbConnector.getInstance().getConnection(), Integer.parseInt(array[2]));
		} catch (NumberFormatException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		IRoomDao<Room> roomDao = DependencyInjection.getInstance().getInterfacePair(IRoomDao.class);
		Room room = null;
		try {
			room = roomDao.getById(DbConnector.getInstance().getConnection(), Integer.parseInt(array[3]));
		} catch (NumberFormatException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date dateStart = null;
		Date dateFinish = null;

		if (!array[4].equals("null")) {
			try {
				dateStart = formatter.parse(array[4]);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		if (!array[5].equals("null")) {
			try {
				dateFinish = formatter.parse(array[5]);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		Order result = new Order();

		result.setId(Integer.parseInt(array[0]));
		result.setNum(Integer.parseInt(array[1]));
		result.setClient(client);
		result.setRoom(room);
		result.setStartDate(dateStart);
		result.setFinishDate(dateFinish);

		@SuppressWarnings("unchecked")
		IServiceDao<Service> serviceDao = DependencyInjection.getInstance().getInterfacePair(IServiceDao.class);

		for (int i = 6; i < array.length; i++) {
			Service service;
			try {
				service = serviceDao.getById(DbConnector.getInstance().getConnection(), Integer.parseInt(array[i]));
				result.addService(service);
			} catch (NumberFormatException | SQLException e) {
				logger.error(e);
			}
		}
		return result;
	}
}

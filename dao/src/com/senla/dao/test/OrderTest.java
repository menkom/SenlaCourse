package com.senla.dao.test;

import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.model.Order;

public class OrderTest {

	private static final Logger logger = Logger.getLogger(OrderTest.class);
//	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public static void testOrderDao() throws ClassNotFoundException {

		@SuppressWarnings("unchecked")
		IOrderDao<Order> dao = DependencyInjection.getInstance().getInterfacePair(IOrderDao.class);
		// getAll
		try {
			List<Order> orders = dao.getAll(DbConnector.getInstance().getConnection(), "");

			for (Order order : orders) {
				System.out.println(order);
			}
		} catch (SQLException e) {
			System.out.println(e);
			logger.error(e);
		}

		// insert
		Order order = new Order();
//		order.setNum(701);
//		try {
//			order.setClient((new ClientDaoV2()).getById(DbConnector.getInstance().getConnection(), 3));
//			order.setRoom((new RoomDaoV2()).getById(DbConnector.getInstance().getConnection(), 3));
//			order.setStartDate(formatter.parse("2018-10-04"));
//			System.out.println("Order add:" + dao.add(DbConnector.getInstance().getConnection(), order));
//			System.out.println("Order ID:" + order.getId());
//		} catch (SQLException e) {
//			System.out.println(e);
//		} catch (ParseException e) {
//			System.out.println(e);
//		}

		// update
		try {
			order = dao.getById(DbConnector.getInstance().getConnection(), 1);
			order.setNum(order.getNum() + 1);
			System.out.println("update:" + dao.update(DbConnector.getInstance().getConnection(), order));
		} catch (SQLException e) {
			logger.error(e);
			System.out.println(e);
		}
//		System.out.println("orderId[1] : " + order);

	}

}

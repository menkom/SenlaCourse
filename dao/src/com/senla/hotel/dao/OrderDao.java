package com.senla.hotel.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.dao.DaoHandler;
import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.api.IOrderRepository;

public class OrderDao implements IOrderRepository {

	private static final String ERROR_DATE_FORMAT = "Date format error.";
	private static final Logger logger = Logger.getLogger(OrderDao.class);
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private static IOrderRepository orderRepository;

	private OrderDao() {
		super();
	}

	public static IOrderRepository getInstance() {
		if (orderRepository == null) {
			orderRepository = DependencyInjection.getInstance().getInterfacePair(IOrderRepository.class);
		}
		return orderRepository;
	}

	@Override
	public List<Order> getOrders() {
		List<Order> resultList = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("SELECT * FROM `order`");

			while (resultSet.next()) {
				resultList.add(parseResultSet(resultSet));
			}

		} catch (SQLException e) {
			logger.error(e);
		}
		for (Order order : resultList) {
			order.addServices(getServices(order.getId()));
		}
		return resultList;
	}

	@Override
	public boolean add(Order order) {
		int result = 0;
		try {
			String dateStart = null;
			if (order.getStartDate() != null) {
				dateStart = "\'" + formatter.format(order.getStartDate()) + "\'";
			}
			String dateFinish = null;
			if (order.getFinishDate() != null) {
				dateFinish = "\'" + formatter.format(order.getFinishDate()) + "\'";
			}

			DaoHandler.getInstance().setConnectionAutoCommit(false);

			result = DaoHandler.getInstance().executeUpdate(
					"insert into `order` (order_id, order_num, order_client_id, order_room_id, order_start_date, order_finish_date) values ("
							+ order.getId() + ", " + order.getNum() + ", " + order.getClient().getId() + ", "
							+ order.getRoom().getId() + ", " + dateStart + ", " + dateFinish + ")");
			if (result > 0) {
				ResultSet rs = DaoHandler.getInstance().executeQuery("select last_insert_id()");
				order.setId(parseLastId(rs));

//TODO dont forget about saving and services

				if (order.getServices().size() > 0) {
					StringBuilder insertSO = new StringBuilder(
							"insert into `service_order` (so_service_id, sc_order_id) values +");

					for (Service service : order.getServices()) {
						insertSO.append("(" + service.getId() + ", " + order.getId() + "),");
					}
					insertSO.deleteCharAt(insertSO.length() - 1);
					result = DaoHandler.getInstance().executeUpdate(insertSO.toString());
				}
				DaoHandler.getInstance().setConnectionCommit();
			}
			DaoHandler.getInstance().setConnectionAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e);
			DaoHandler.getInstance().setConnectionRollback();
		}
		return result > 0;
	}

	@Override
	public boolean addAll(List<Order> orders) {
		int result = 0;
		try {
			StringBuilder query = new StringBuilder(
					"insert into `order` (order_id, order_num, order_client_id, order_room_id, order_start_date, order_finish_date) values ");

			for (Order order : orders) {
				String dateStart = null;
				if (order.getStartDate() != null) {
					dateStart = "\'" + formatter.format(order.getStartDate()) + "\'";
				}
				String dateFinish = null;
				if (order.getFinishDate() != null) {
					dateFinish = "\'" + formatter.format(order.getFinishDate()) + "\'";
				}
				query.append("(" + order.getId() + ", " + order.getNum() + ", " + order.getClient().getId() + ", "
						+ order.getRoom().getId() + ", " + dateStart + ", " + dateFinish + "),");
			}
			query.deleteCharAt(query.length() - 1);
			result = DaoHandler.getInstance().executeUpdate(query.toString());
			// TODO dont forget about saving and services
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean delete(Integer orderNum) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from `order` where order_num=\'" + orderNum + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from `order` where order_id=\'" + id + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public Order getOrderByNum(Integer num) {
		Order order = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from `order` where order_num=\'" + num + "\'");

			while (resultSet.next()) {
				order = parseResultSet(resultSet);
				return order;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return order;
	}

	@Override
	public Order getOrderById(Integer id) {
		Order order = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from `order` where order_id=\'" + id + "\'");

			while (resultSet.next()) {
				order = parseResultSet(resultSet);
				return order;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return order;
	}

	@Override
	public boolean addOrderService(int id, Service service) {
		int result = 0;
		try {
			result = DaoHandler.getInstance()
					.executeUpdate("insert into `service_order` (so_service_id, so_order_id) values (" + id + ", "
							+ service.getId() + ")");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	public List<Service> getServices(int orderId) {
		List<Service> services = new ArrayList<>();

		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery(
					"SELECT s.* FROM service s, service_order so WHERE so.so_service_id=s.service_id and so.so_order_id=\'"
							+ orderId + "\'");

			while (resultSet.next()) {
				Service service = ((ServiceDao) ServiceDao.getInstance()).parseResultSet(resultSet);
				services.add(service);
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return services;
	}

	@Override
	public boolean update(Order order) {
		int result = 0;
		try {
			String dateStart = null;
			if (order.getStartDate() != null) {
				dateStart = "\'" + formatter.format(order.getStartDate()) + "\'";
			}
			String dateFinish = null;
			if (order.getFinishDate() != null) {
				dateFinish = "\'" + formatter.format(order.getFinishDate()) + "\'";
			}

			result = DaoHandler.getInstance()
					.executeUpdate("update order set order_num=" + order.getNum() + ", order_client_id=\'"
							+ order.getClient().getId() + "\', order_room_id=" + order.getRoom().getId()
							+ ", order_start_date=" + dateStart + ", order_finish_date=" + dateFinish
							+ " where order_id=\'" + order.getId() + "\'");
//TODO list of services need update too.
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	private int parseLastId(ResultSet resultSet) throws SQLException {
		return resultSet.getInt("last_insert_id()");
	}

	public Order parseResultSet(ResultSet resultSet) throws SQLException {
		Order order = new Order();

		order.setId(resultSet.getInt("order_id"));
		order.setNum(resultSet.getInt("order_num"));

		// TODO Implement DI
		Client client = ((ClientDao) ClientDao.getInstance()).getClientById(resultSet.getInt("order_client_id"));
		order.setClient(client);

		// TODO Implement DI
		Room room = ((RoomDao) RoomDao.getInstance()).getRoomById(resultSet.getInt("order_room_id"));
		order.setRoom(room);

		order.setStartDate(resultSet.getDate("order_start_date"));

		String dateStr = resultSet.getString("order_finish_date");

		if ((dateStr != null) && !dateStr.equals("0000-00-00")) {
			try {
				order.setFinishDate(formatter.parse(dateStr));
			} catch (ParseException e) {
				logger.error(ERROR_DATE_FORMAT, e);
			}
		}
		return order;
	}
}

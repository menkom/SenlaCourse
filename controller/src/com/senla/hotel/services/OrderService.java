package com.senla.hotel.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.services.api.IOrderService;
import com.senla.util.ExportCSV;

public class OrderService implements IOrderService {

	private static final Logger logger = Logger.getLogger(OrderService.class);

	private static IOrderService orderService;

	private DbConnector dbConnector;

	private IOrderDao<Order> orderDao;
	private IRoomDao<Room> roomDao;
	private IClientDao<Client> clientDao;

	@SuppressWarnings("unchecked")
	private OrderService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.orderDao = DependencyInjection.getInstance().getInterfacePair(IOrderDao.class);
		this.roomDao = DependencyInjection.getInstance().getInterfacePair(IRoomDao.class);
		this.clientDao = DependencyInjection.getInstance().getInterfacePair(IClientDao.class);
	}

	public static IOrderService getInstance() {
		if (orderService == null) {
			orderService = DependencyInjection.getInstance().getInterfacePair(IOrderService.class);
		}
		return orderService;
	}

	@Override
	public boolean add(Order order) throws SQLException {
		return orderDao.add(dbConnector.getConnection(), order);
	}

	@Override
	public boolean addAll(List<Order> orders) throws SQLException {
		
		boolean result = false;
		Connection connection = dbConnector.getConnection();
		connection.setAutoCommit(false);
		try {
			result = orderDao.addAll(connection, orders);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e);
			connection.rollback();
			connection.setAutoCommit(true);
			throw e;
		}
		return result;
	}

	@Override
	public boolean addOrder(int num, int clientId, int roomId, Date startDate, Date finishDate) throws SQLException {
		Client client = ClientService.getInstance().getClientById(clientId);
		Room room = RoomService.getInstance().getRoomById(roomId);

		Order order = new Order(num, client, room, startDate, finishDate);
		return orderDao.add(dbConnector.getConnection(), order);
	}

	@Override
	public boolean update(Order order) throws SQLException {
		return orderDao.update(dbConnector.getConnection(), order);
	}

	@Override
	public List<Order> getOrders() throws SQLException {
		return orderDao.getAll(dbConnector.getConnection(), "");
	}

	@Override
	public Order getOrderById(int id) throws SQLException {
		return orderDao.getById(dbConnector.getConnection(), id);
	}

	@Override
	public boolean orderRoom(int orderNum, int roomId, int clientId, Date dateStart, Date dateFinish)
			throws SQLException {
		boolean result = false;

		Connection connection = dbConnector.getConnection();
		try {
			connection.setAutoCommit(false);
			Room room = roomDao.getById(connection, roomId);
			Client client = clientDao.getById(connection, clientId);
			Order order = new Order(orderNum, client, room, dateStart, dateFinish);
			result = orderDao.add(connection, order);
			if (order.getStartDate().equals(new Date())) {
				order.getRoom().setStatus(RoomStatus.OCCUPIED);
				roomDao.update(connection, order.getRoom());
			}
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e);
			connection.rollback();
			connection.setAutoCommit(true);
			throw e;
		}

		return result;
	}

	@Override
	public boolean freeRoom(int orderId) throws SQLException {
		boolean result = false;
		Connection connection = dbConnector.getConnection();
		try {
			connection.setAutoCommit(false);
			Order order = orderDao.getById(connection, orderId);

			if (order != null) {
				order.setFinishDate(new Date());
				order.getRoom().setStatus(RoomStatus.AVAILABLE);
				result = orderDao.update(connection, order);
			}
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e);
			connection.rollback();
			connection.setAutoCommit(true);
			throw e;
		}

		return result;
	}

	@Override
	public boolean addOrderService(int orderId, int serviceId) throws SQLException {
		Service service = ServiceService.getInstance().getServiceById(serviceId);
		return orderDao.addOrderService(dbConnector.getConnection(), orderId, service);
	}

	@Override
	public Integer getOrderPrice(int orderId) throws SQLException {
		Order order = orderDao.getById(dbConnector.getConnection(), orderId);
		Integer result = null;
		if (order != null) {
			if (order.getFinishDate() == null) {
				result = 0;
			} else {
				Date d1 = order.getStartDate();
				Date d2 = order.getFinishDate();

				int daysBetween = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

				result = daysBetween * order.getRoom().getPrice();

				for (Service service : order.getServices()) {
					result += service.getPrice();
				}
			}
		}
		return result;
	}

	@Override
	public List<Order> getActiveOrders(EnumOrderSort orderSort) throws SQLException {
		return orderDao.getActiveOrders(dbConnector.getConnection(), orderSort);
	}

	@Override
	public List<Order> getOrdersByRoom(int roomId) throws SQLException {
		return orderDao.getOrdersByRoom(dbConnector.getConnection(), roomId);
	}

	@Override
	public List<Order> getLastOrdersByRoom(int roomId, int maxOrders, EnumOrderSort orderSort) throws SQLException {
		return orderDao.getLastOrdersByRoom(dbConnector.getConnection(), roomId, maxOrders, orderSort);
	}

	@Override
	public List<Service> getOrderServices(int orderId, EnumServiceSort serviceSort) throws SQLException {
		return orderDao.getServices(dbConnector.getConnection(), orderId, serviceSort.getTableField());
	}

	@Override
	public Order cloneOrder(int orderId) throws CloneNotSupportedException, SQLException {
		Connection connection = dbConnector.getConnection();
		Order orderToClone = orderDao.getById(connection, orderId);
		if (orderToClone == null) {
			return null;
		} else {
			Order result = orderToClone.clone();
			orderDao.add(connection, result);
			return result;
		}
	}

	@Override
	public boolean exportOrderCSV(int orderId, String fileName) throws IOException, SQLException {
		Order order = orderDao.getById(dbConnector.getConnection(), orderId);
		if (order == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(order.toString(), fileName);
		}
	}

	@Override
	public boolean importOrdersCSV(String file) throws IOException, SQLException {
		boolean result = false;
		Connection connection = dbConnector.getConnection();
		try {
			connection.setAutoCommit(false);
			List<Order> orders = ExportCSV.getOrdersFromCSV(file);
			for (Order order : orders) {
				if (orderDao.getById(connection, order.getId()) != null) {
					result = orderDao.update(connection, order);
				} else {
					result = orderDao.add(connection, order);
				}
				if (!result) {
					break;
				}
			}
			connection.setAutoCommit(true);
		} catch (IOException | SQLException e) {
			logger.error(e);
			connection.rollback();
			connection.setAutoCommit(true);
			throw e;
		}
		return result;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException, SQLException {
		return orderDao.exportCsv(dbConnector.getConnection(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException, SQLException {
		return orderDao.importCsv(dbConnector.getConnection(), csvFilePath);
	}

}
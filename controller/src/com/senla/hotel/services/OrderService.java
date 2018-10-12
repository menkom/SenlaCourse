package com.senla.hotel.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private static final String SELECT_ORDERS_BY_ROOM = "select * from `order` o join client join room "
			+ "on o.order_client_id=client.client_id and o.order_room_id=room.room_id and " + "room.room_id=?";

	private static final String SELECT_ACTIVE_ORDERS = "select * from `order` o join client join room "
			+ "on o.order_client_id=client.client_id and o.order_room_id=room.room_id and "
			+ "o.order_start_date<=? and (o.order_finish_date=null or o.order_finish_date>=?) " + "order by (?)";

	private static final String SELECT_LAST_ROOM_ORDERS = "select * from `order` o join client join room "
			+ "on o.order_client_id=client.client_id and o.order_room_id=room.room_id  "
			+ "and room.room_id=? order by (-o.order_start_date) limit  ?";

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private static IOrderService orderService;

	private DbConnector dbConnector;

	private IOrderDao<Order> orderDao;

	@SuppressWarnings("unchecked")
	private OrderService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.orderDao = DependencyInjection.getInstance().getInterfacePair(IOrderDao.class);
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
		return orderDao.addAll(dbConnector.getConnection(), orders);
	}

	@Override
	public boolean addOrder(int num, int clientId, int roomId, Date startDate, Date finishDate) throws SQLException {
		Client client = ClientService.getInstance().getClientById(clientId);
		Room room = RoomService.getInstance().getRoomById(roomId);

		Order order = new Order(num, client, room, startDate, finishDate);
		return add(order);
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
		@SuppressWarnings("unchecked")
		IRoomDao<Room> roomDao = DependencyInjection.getInstance().getInterfacePair(IRoomDao.class);
		Room room = roomDao.getById(dbConnector.getConnection(), roomId);

		@SuppressWarnings("unchecked")
		IClientDao<Client> clientDao = DependencyInjection.getInstance().getInterfacePair(IClientDao.class);
		Client client = clientDao.getById(dbConnector.getConnection(), clientId);
		Order order = new Order(orderNum, client, room, dateStart, dateFinish);
		result = add(order);
		if (order.getStartDate().equals(new Date())) {
			order.getRoom().setStatus(RoomStatus.OCCUPIED);
		}
		return result;
	}

	@Override
	public boolean freeRoom(int orderId) throws SQLException {
		boolean result = false;
		Order order = orderDao.getById(dbConnector.getConnection(), orderId);

		if (order != null) {
			order.setFinishDate(new Date());
			order.getRoom().setStatus(RoomStatus.AVAILABLE);
			result = orderDao.update(dbConnector.getConnection(), order);
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
		Order order = getOrderById(orderId);
		Integer result;
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
		return result;
	}

	@Override
	public List<Order> getActiveOrders(EnumOrderSort orderSort) throws SQLException {
		List<Order> result = new ArrayList<>();
		try (PreparedStatement ps = dbConnector.getConnection().prepareStatement(SELECT_ACTIVE_ORDERS)) {
			ps.setString(1, formatter.format(new Date()));
			ps.setString(2, formatter.format(new Date()));
			ps.setString(3, orderSort.getTableField());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Order order = orderDao.parseResultSet(resultSet);
				result.add(order);
			}
		}
		return result;
	}

	@Override
	public List<Order> getOrdersByRoom(int roomId) throws SQLException {

		List<Order> result = new ArrayList<>();
		try (PreparedStatement ps = dbConnector.getConnection().prepareStatement(SELECT_ORDERS_BY_ROOM)) {
			ps.setInt(1, roomId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Order order = orderDao.parseResultSet(resultSet);
				result.add(order);
			}
		}

		return result;
	}

	@Override
	public List<Order> getLastOrdersByRoom(int roomId, int maxOrders, EnumOrderSort orderSort) throws SQLException {
		List<Order> result = new ArrayList<>();

		try (PreparedStatement ps = dbConnector.getConnection().prepareStatement(SELECT_LAST_ROOM_ORDERS)) {
			ps.setInt(1, roomId);
			ps.setInt(2, maxOrders);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Order order = orderDao.parseResultSet(resultSet);
				result.add(order);
			}
		}
		return result;
	}

	@Override
	public List<Service> getOrderServices(int orderId, EnumServiceSort serviceSort) throws SQLException {
		return orderDao.getServices(dbConnector.getConnection(), orderId, serviceSort.getTableField());
	}

	@Override
	public Order cloneOrder(int orderId) throws CloneNotSupportedException, SQLException {
		Order orderToClone = orderDao.getById(dbConnector.getConnection(), orderId);
		if (orderToClone == null) {
			return null;
		} else {
			return orderToClone.clone();
		}
	}

	@Override
	public boolean exportOrderCSV(int orderId, String fileName) throws IOException, SQLException {
		Order order = getOrderById(orderId);
		if (order == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(order.toString(), fileName);
		}
	}

	@Override
	public boolean importOrdersCSV(String file) throws IOException, SQLException {
		boolean result = false;
		List<Order> orders = ExportCSV.getOrdersFromCSV(file);
		for (Order order : orders) {
			if (getOrderById(order.getId()) != null) {
				result = update(order);
			} else {
				result = add(order);
			}
			if (!result) {
				break;
			}
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
package com.senla.hotel.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.services.api.IOrderService;
import com.senla.util.ExportCSV;

public class OrderService implements IOrderService {

	private static IOrderService orderService;

	private DbConnector dbConnector;

	private IOrderDao<Order> orderDao;

	@SuppressWarnings("unchecked")
	public OrderService() throws ClassNotFoundException {
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
	public List<Order> getActiveOrders(Comparator<Order> comparator) throws SQLException {
		List<Order> result = new ArrayList<>();
//TODO Remake it to database filter
		for (Order order : orderDao.getAll(dbConnector.getConnection(), "start_date")) {
			Date currentDate = new Date();
			if (order != null) {
				if (currentDate.after(order.getStartDate())
						&& ((order.getFinishDate() == null) || (currentDate.before(order.getFinishDate())))) {
					result.add(order);
				}
			}
		}
		result.sort(comparator);
		return result;
	}

	@Override
	public List<Order> getOrdersByRoom(int num) throws SQLException {

		List<Order> result = new ArrayList<>();
//TODO do it to DB
		for (Order order : orderDao.getAll(dbConnector.getConnection(), "")) {
			if (order != null) {
				if (order.getRoom().getNumber() == num) {
					result.add(order);
				}
			}
		}
		return result;
	}

	@Override
	public List<Order> getLastOrdersByRoom(int num, int maxOrders, Comparator<Order> comparator) throws SQLException {
//TODO DB again
		List<Order> result = new ArrayList<>();
		List<Order> orders = getOrdersByRoom(num);
		orders.sort(new OrderSortByFinishDate());

		int j = 0;
		for (int i = orders.size() - 1; i >= 0; i--) {
			if (orders.get(i) != null) {
				result.add(orders.get(i));
				if (j == maxOrders - 1) {
					break;
				} else {
					j++;
				}
			}
		}
		result.sort(comparator);
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
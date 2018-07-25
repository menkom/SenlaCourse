package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.OrderRepository;

public class OrderService implements IService {

	private OrderRepository orderRepository;

	private static OrderService orderService;
	private int nextNum;

	private OrderService() {
		super();
		this.orderRepository = OrderRepository.getInstance();

		this.nextNum = 4;
	}

	public String getFileName() {
		return "order.db";
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public static OrderService getInstance() {
		if (orderService == null) {
			orderService = new OrderService();
		}
		return orderService;
	}

	public void add(Order element) {
		orderRepository.add(element);
	}

	public void addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {
		Client client = ClientService.getInstance().getClientByName(clientName);
		Room room = RoomService.getInstance().getRoomByNum(roomNum);

		Order element = new Order(num, client, room, startDate, finishDate);
		add(element);
	}

	public Order getOrderByNum(int num) {
		return getOrderRepository().getOrderByNum(num);
	}

	public void orderRoom(int roomNum, String clientName, Date dateStart, Date dateFinish) {
		// We have to create Order to change room status. By adding order you don't have
		// access to order and in information to change room status.
		Room room = RoomService.getInstance().getRoomByNum(roomNum);
		Order order = new Order(getNextNum(), ClientService.getInstance().getClientByName(clientName), room, dateStart,
				dateFinish);
		this.add(order);
		if (order.getStartDate() == new Date()) {
			order.getRoom().setStatus(RoomStatus.OCCUPIED);
		}
	}

	public void freeRoom(int orderNum) {
		Order order = orderRepository.getOrderByNum(orderNum);

		if (order != null) {
			order.setFinishDate(new Date());
			order.getRoom().setStatus(RoomStatus.AVAILABLE);
		}
	}

	public void addOrderService(int orderNum, int serviceCode) {
		Service service = ServiceService.getInstance().getServiceByCode(serviceCode);
		getOrderRepository().addOrderService(orderNum, service);
	}

	public int getOrderPrice(int orderNum) {
		Order order = getOrderByNum(orderNum);
		int result;
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

	// Get all active orders
	public List<Order> getActiveOrders() {
		List<Order> result = new ArrayList<>();

		for (Order order : getOrderRepository().getOrders()) {
			Date currentDate = new Date();
			if (order != null) {
				if (currentDate.after(order.getStartDate())
						&& ((currentDate.before(order.getFinishDate()) || order.getFinishDate() == null))) {
					result.add(order);
				}
			}
		}
		return result;
	}

	public List<Order> getOrdersByRoom(int num) {

		List<Order> result = new ArrayList<>();

		for (Order order : getOrderRepository().getOrders()) {
			if (order != null) {
				if (order.getRoom().getNumber() == num) {
					result.add(order);
				}
			}
		}
		return result;
	}

	public List<Order> getLastThreeOrdersByRoom(int num) {

		List<Order> result = new ArrayList<>();
		List<Order> orders = getOrdersByRoom(num);
		orders.sort(new OrderSortByFinishDate());

		int j = 0;
		for (int i = orders.size() - 1; i >= 0; i--) {
			if (orders.get(i) != null) {
				result.add(orders.get(i));
				if (j == 2) {
					break;
				} else {
					j++;
				}
			}
		}
		return result;
	}

	public int getNextNum() {
		int result = nextNum;
		nextNum++;
		return result;
	}

	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(dbPath + getFileName()).readFromFile();

		setNextNum(Integer.parseInt(array[0]));

		orderRepository.setOrders(ListConverter.getOrders(Arrays.copyOfRange(array, 1, array.length)));
	}

	public void saveToDB(String dbPath) {
		String[] repositoryToStr = ListConverter.getArrayFromList(orderRepository.getOrders());

		String[] result = new String[repositoryToStr.length + 1];

		result[0] = Integer.toString(nextNum);

		System.arraycopy(repositoryToStr, 0, result, 1, repositoryToStr.length);

		new TextFileWorker(dbPath + getFileName()).writeToFile(result);
	}

	public static OrderService getOrderService() {
		if (orderService == null) {
			orderService = new OrderService();
		}
		return orderService;
	}

}
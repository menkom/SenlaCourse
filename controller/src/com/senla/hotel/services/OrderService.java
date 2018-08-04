package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.exception.NoEntryException;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.OrderRepository;

public class OrderService implements IService {

	private static OrderService orderService;

	private OrderRepository orderRepository;
	private int nextNum;

	private OrderService() {
		super();
		this.orderRepository = OrderRepository.getInstance();

		this.nextNum = 4;
	}

	public static OrderService getInstance() {
		if (orderService == null) {
			orderService = new OrderService();
		}
		return orderService;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void add(Order order) {
		orderRepository.add(order);
	}

	public void addOrder(Integer num, String clientName, Integer roomNum, Date startDate, Date finishDate)
			throws NoEntryException {
		Client client = ClientService.getInstance().getClientByName(clientName);
		Room room = RoomService.getInstance().getRoomByNum(roomNum);

		Order order = new Order(num, client, room, startDate, finishDate);
		add(order);
	}

	public Order getOrderByNum(int num) {
		return getOrderRepository().getOrderByNum(num);
	}

	public Order orderRoom(Integer roomNum, String clientName, Date dateStart, Date dateFinish)
			throws NoEntryException {
		Room room = RoomService.getInstance().getRoomByNum(roomNum);
		Order order = new Order(getNextNum(), ClientService.getInstance().getClientByName(clientName), room, dateStart,
				dateFinish);
		this.add(order);
		if (order.getStartDate() == new Date()) {
			order.getRoom().setStatus(RoomStatus.OCCUPIED);
		}
		return order;
	}

	public boolean freeRoom(Integer orderNum) {
		boolean result = false;
		Order order = orderRepository.getOrderByNum(orderNum);

		if (order != null) {
			order.setFinishDate(new Date());
			order.getRoom().setStatus(RoomStatus.AVAILABLE);
			result = true;
		}
		return result;
	}

	public void addOrderService(Integer orderNum, Integer serviceCode) {
		Service service = ServiceService.getInstance().getServiceByCode(serviceCode);
		getOrderRepository().addOrderService(orderNum, service);
	}

	public int getOrderPrice(Integer orderNum) {
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

	public List<Order> getActiveOrders(Comparator<Order> comparator) {
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
		result.sort(comparator);
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

	public List<Order> getLastOrdersByRoom(int num, int maxOrders, Comparator<Order> comparator) {

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

	public int getNextNum() {
		int result = nextNum;
		nextNum++;
		return result;
	}

	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}

	public void loadFromFile(String filePath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(filePath + "order.db").readFromFile();

		setNextNum(Integer.parseInt(array[0]));

		orderRepository.getOrders().addAll(ListConverter.getOrders(Arrays.copyOfRange(array, 1, array.length)));
	}

	public void saveToFile(String filePath) {
		String[] repositoryToStr = ListConverter.getArrayFromList(orderRepository.getOrders());

		String[] result = new String[repositoryToStr.length + 1];

		result[0] = Integer.toString(nextNum);

		System.arraycopy(repositoryToStr, 0, result, 1, repositoryToStr.length);

		new TextFileWorker(filePath + "order.db").writeToFile(result);
	}

	public List<Service> getOrderServices(int orderNum, Comparator<Service> comparator) {
		List<Service> result = null;
		Order order = getOrderByNum(orderNum);
		if ((order != null) && (order.getServices() != null)) {
			result = order.getServices();
			result.sort(comparator);
		}
		return result;
	}

}
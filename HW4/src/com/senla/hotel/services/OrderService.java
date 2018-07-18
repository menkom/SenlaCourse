package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import com.senla.base.BaseObject;
import com.senla.hotel.repository.ClientRepository;
import com.senla.hotel.repository.IBaseRepository;
import com.senla.hotel.repository.OrderRepository;
import com.senla.hotel.repository.RoomRepository;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.util.ArrayOperator;
import com.senla.util.DateOperator;
import com.senla.util.FileOperator;

public class OrderService implements IService {

	private ClientRepository clientRepository;
	private RoomRepository roomRepository;
	private ServiceRepository serviceRepository;
	private OrderRepository orderRepository;
	private int nextNum;

	public OrderService(ClientRepository clientRepository, RoomRepository roomRepository,
			ServiceRepository serviceRepository, OrderRepository orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;

		this.nextNum = 4;
	}

	public String getFileName() {
		return "order.db";
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public ServiceRepository getServiceRepository() {
		return serviceRepository;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void add(BaseObject element) {
		getOrderRepository().add(element);
	}

	public void addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {
		Client client = getClientRepository().getClientByName(clientName);
		Room room = getRoomRepository().getRoomByNum(roomNum);

		Order element = new Order(num, client, room, startDate, finishDate);
		getRepository().add(element);
	}

	public Order getOrderByNum(int num) {
		return getOrderRepository().getOrderByNum(num);
	}

	public void orderRoom(int roomNum, String clientName, Date dateStart, Date dateFinish) {
		// We have to create Order to change room status. By adding order you don't have
		// access to order and in information to change room status.
		Order order = new Order(getNexNum(), getClientRepository().getClientByName(clientName),
				getRoomRepository().getRoomByNum(roomNum), dateStart, dateFinish);
		add(order);
		if (order.getStartDate() == new Date()) {
			order.getRoom().setStatus(RoomStatus.OCCUPIED);
		}
	}

	public void freeRoom(int orderNum) {
		Order order = ((OrderRepository) getRepository()).getOrderByNum(orderNum);

		if (order != null) {
			order.setFinishDate(new Date());
			order.getRoom().setStatus(RoomStatus.AVAILABLE);
		}
	}

	public void addOrderService(int orderNum, int serviceCode) {
		Service service = getServiceRepository().getServiceByCode(serviceCode);
		getOrderRepository().addOrderService(orderNum, service);
	}

	public int getOrderPrice(int orderNum) {
		Order order = getOrderByNum(orderNum);
		int result;
		if (order.getFinishDate() == null) {
			result = 0;
		} else {
			result = DateOperator.daysBetween(order.getStartDate(), order.getFinishDate()) * order.getRoom().getPrice();

			for (Service service : order.getServices()) {
				result += service.getPrice();
			}
		}
		return result;
	}

	// Get all active orders
	public Order[] getActiveOrders() {
		Order[] result = new Order[0];

		for (Order order : (Order[]) getOrderRepository().getRepository()) {
			Date currentDate = new Date();
			if (order != null) {
				if (currentDate.after(order.getStartDate())
						&& ((currentDate.before(order.getFinishDate()) || order.getFinishDate() == null))) {
					result = (Order[]) ArrayOperator.add(result, order);
				}
			}
		}
		return result;
	}

	public Order[] getOrdersByRoom(int num) {

		Order[] result = new Order[ArrayOperator.MINIMUM_ARRAY_LENGTH];

		for (Order order : (Order[]) getOrderRepository().getRepository()) {
			if (order != null) {
				if (order.getRoom().getNumber() == num) {
					result = (Order[]) ArrayOperator.add(result, order);
				}
			}
		}
		return result;
	}

	public Order[] getLastThreeOrdersByRoom(int num) {

		Order[] result = new Order[3];
		Order[] orders = getOrdersByRoom(num);
		Arrays.sort(orders, new OrderSortByFinishDate());

		int j = 0;
		for (int i = orders.length - 1; i >= 0; i--) {
			if (orders[i] != null) {
				result = (Order[]) ArrayOperator.add(result, orders[i]);
				if (j == 2) {
					break;
				} else {
					j++;
				}
			}
		}
		return result;
	}

	public String[] getStringToSave() {
		String[] repositoryToStr = getRepository().toStringArray();
		String[] result = new String[repositoryToStr.length + 1];

		result[0] = Integer.toString(nextNum);

		System.arraycopy(repositoryToStr, 0, result, 1, repositoryToStr.length);
		return result;
	}

	@Override
	public IBaseRepository getRepository() {
		return getOrderRepository();
	}

	public int getNexNum() {
		int result = nextNum;
		nextNum++;
		return result;
	}

	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		OrderService service = new FileOperator().getOrderService(dbPath + getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setRepository(((OrderRepository) service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) {
		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

}
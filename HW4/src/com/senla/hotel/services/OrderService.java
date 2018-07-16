package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import com.senla.base.BaseObject;
import com.senla.hotel.repository.ClientRepository;
import com.senla.hotel.repository.IBaseRepository;
import com.senla.hotel.repository.OrderRepository;
import com.senla.hotel.repository.RoomRepository;
import com.senla.hotel.repository.ServiceRepository;
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
		order.getRoom().setStatus(RoomStatus.OCCUPIED);
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

	public int getPrice(int orderNum) {
		Order order = getOrderByNum(orderNum);
		int result;
		if (order.getFinishDate() == null) {
			result = 0;
		} else {
			result = new DateOperator().daysBetween(order.getStartDate(), order.getFinishDate())
					* order.getRoom().getPrice();

			for (Service service : order.getServices()) {
				result += service.getPrice();
			}
		}
		return result;
	}

	public Order[] getClientRoom() {
		Order[] result = new Order[0];

		for (Order order : (Order[]) getOrderRepository().getRepository()) {
			Date currentDate = new Date();
			if (currentDate.after(order.getStartDate())
					&& ((currentDate.before(order.getFinishDate()) || order.getFinishDate() == null))) {
				result = (Order[]) ArrayOperator.add(result, order);
			}

		}

		return result;
	}

	public OrderRepository getOrdersByRoom(int num) {

		OrderRepository result = new OrderRepository();

		for (Order order : (Order[]) getOrderRepository().getRepository()) {
			if (order.getRoom().getNumber() == num) {
				result.add(order);
			}
		}

		return result;
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		OrderService service = new FileOperator().getOrderService(dbPath + getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setRepository(((OrderRepository) service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) {
		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
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

}
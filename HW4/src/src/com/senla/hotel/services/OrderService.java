package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import com.senla.base.BaseObject;
import com.senla.hotel.array.ClientArray;
import com.senla.hotel.array.IBaseArray;
import com.senla.hotel.array.OrderArray;
import com.senla.hotel.array.RoomArray;
import com.senla.hotel.array.ServiceArray;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.util.ArrayOperator;
import com.senla.util.DateOperator;
import com.senla.util.FileOperator;

public class OrderService implements IService {

	private ClientArray clientRepository;
	private RoomArray roomRepository;
	private ServiceArray serviceRepository;
	private OrderArray orderRepository;
	private int nextNum;

	public OrderService(ClientArray clientRepository, RoomArray roomRepository, ServiceArray serviceRepository,
			OrderArray orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;

		this.nextNum = 1;
	}

	public String getFileName() {
		return "order.db";
	}

	public ClientArray getClientRepository() {
		return clientRepository;
	}

	public RoomArray getRoomRepository() {
		return roomRepository;
	}

	public ServiceArray getServiceRepository() {
		return serviceRepository;
	}

	public OrderArray getOrderRepository() {
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
		
		order.getRoom().setStatus(RoomStatus.OCCUPIED);
	}

	public void freeRoom(int roomNum) {

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

		for (Order order : (Order[]) getOrderRepository().getArray()) {
			Date currentDate = new Date();
			if (currentDate.after(order.getStartDate())
					&& ((currentDate.before(order.getFinishDate()) || order.getFinishDate() == null))) {
				result = (Order[]) new ArrayOperator().add(result, order);
			}

		}

		return result;
	}

	public OrderArray getOrdersByRoom(int num) {

		OrderArray result = new OrderArray();

		for (Order order : (Order[]) getOrderRepository().getArray()) {
			if (order.getRoom().getNumber() == num) {
				result.add(order);
			}
		}

		return result;
	}

	public void loadFromDB() throws IOException, NumberFormatException, ParseException {
		OrderService service = new FileOperator().getOrderService(getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setArray(((ServiceArray) service.getRepository()).getArray());
	}

	public void saveToDB() {
		new FileOperator().saveToDB(getFileName(), getStringToSave());
	}

	public String[] getStringToSave() {
		String[] repositoryToStr = getRepository().toStringArray();
		String[] result = new String[repositoryToStr.length + 1];

		result[0] = Integer.toString(nextNum);

		System.arraycopy(repositoryToStr, 0, result, 1, repositoryToStr.length);
		return result;
	}

	@Override
	public IBaseArray getRepository() {
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
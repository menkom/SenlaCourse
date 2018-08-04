package com.senla.hotel.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.exception.NoEntryException;
import com.senla.hotel.comparator.OrderSortByClientName;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.comparator.RoomSortByCapacity;
import com.senla.hotel.comparator.RoomSortByPrice;
import com.senla.hotel.comparator.RoomSortByStar;
import com.senla.hotel.comparator.ServiceSortByPrice;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.services.ClientService;
import com.senla.hotel.services.OrderService;
import com.senla.hotel.services.RoomService;
import com.senla.hotel.services.ServiceService;
import com.senla.util.DisplayOperator;

public class Hotel {

	private ClientService clientService;
	private RoomService roomService;
	private ServiceService serviceService;
	private OrderService orderService;

	private static Hotel hotel;

	private static final Logger logger = Logger.getLogger(Hotel.class);

	private Hotel() {
		super();
		this.clientService = ClientService.getInstance();
		this.roomService = RoomService.getInstance();
		this.serviceService = ServiceService.getInstance();
		this.orderService = OrderService.getInstance();
	}

	public static Hotel getInstance() {
		if (hotel == null) {
			hotel = new Hotel();
		}
		return hotel;
	}

	public void load(String filePath) throws NumberFormatException, IOException, ParseException {
		getClientService().loadFromFile(filePath);
		getRoomService().loadFromFile(filePath);
		getServiceService().loadFromFile(filePath);
		getOrderService().loadFromFile(filePath);
	}

	public void save(String filePath) throws Throwable {
		getClientService().saveToFile(filePath);
		getRoomService().saveToFile(filePath);
		getServiceService().saveToFile(filePath);
		getOrderService().saveToFile(filePath);
	}

	public List<Room> getAllRoomsSortByPrice() {
		return getRoomService().getAllRooms(new RoomSortByPrice());
	}

	public List<Room> getAllRoomsSortByCapacity() {
		return getRoomService().getAllRooms(new RoomSortByCapacity());
	}

	public List<Room> getAllRoomsSortByStar() {
		return getRoomService().getAllRooms(new RoomSortByStar());
	}

	public List<Room> getFreeRoomsSortByPrice() {
		return getRoomService().getFreeRooms(new RoomSortByPrice());
	}

	public List<Room> getFreeRoomsSortByCapacity() {
		return getRoomService().getFreeRooms(new RoomSortByCapacity());
	}

	public List<Room> getFreeRoomsSortByStar() {
		return getRoomService().getFreeRooms(new RoomSortByStar());
	}

	public List<Room> getFreeRoomsByDateSortByPrice(Date date) {
		return getRoomService().getFreeRooms(date, new RoomSortByPrice());
	}

	public List<Room> getFreeRoomsByDateSortByCapacity(Date date) {
		return getRoomService().getFreeRooms(date, new RoomSortByCapacity());
	}

	public List<Room> getFreeRoomsByDateSortByStar(Date date) {
		return getRoomService().getFreeRooms(date, new RoomSortByStar());
	}

	public int getNumberOfFreeRooms() {
		return getRoomService().getNumberOfFreeRooms();
	}

	public int getNumberOfClients() {
		return getClientService().getNumberOfClients();
	}

	public List<Order> getActiveOrdersSortByName() {
		return getOrderService().getActiveOrders(new OrderSortByClientName());
	}

	public List<Order> getActiveOrdersSortByFinishDate() {
		return getOrderService().getActiveOrders(new OrderSortByFinishDate());
	}

	public int getOrderPrice(int orderNum) {
		return getOrderService().getOrderPrice(orderNum);
	}

	public Room getRoomByNum(int roomNum) {
		return getRoomService().getRoomByNum(roomNum);
	}

	public List<Order> getLastOrdersByRoom(int roomNum, int maxOrders) {
		return getOrderService().getLastOrdersByRoom(roomNum, maxOrders, new OrderSortByFinishDate());
	}

	public List<Service> getOrderServices(int orderNum) {
		return getOrderService().getOrderServices(orderNum, new ServiceSortByPrice());
	}

	public List<Service> getAllServicesSortByPrice() {
		return getServiceService().getAllServices(new ServiceSortByPrice());
	}

	public void addClient(String name) {
		getClientService().add(new Client(name));
	}

	public void addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		getRoomService().addRoom(number, capacity, star, status, price);
	}

	public void addService(int code, String name, int price) {
		getServiceService().addService(code, name, price);
	}

	public void addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {

		try {
			getOrderService().addOrder(num, clientName, roomNum, startDate, finishDate);
		} catch (NoEntryException e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}
	}

	public Order orderRoom(String clientName, int roomNum, Date dateStart, Date dateFinish) {
		Order order = null;
		try {
			order = getOrderService().orderRoom(roomNum, clientName, dateStart, dateFinish);
		} catch (NoEntryException e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}
		return order;
	}

	public void addOrderService(int orderNum, int serviceCode) {
		getOrderService().addOrderService(orderNum, serviceCode);
	}

	public boolean freeRoom(int orderNum) {
		return getOrderService().freeRoom(orderNum);
	}

	public void changeRoomStatus(int roomNum, RoomStatus roomStatus) {
		getRoomService().changeRoomStatus(roomNum, roomStatus);
	}

	public void changeRoomPrice(int roomNum, int newPrice) {
		getRoomService().changeRoomPrice(roomNum, newPrice);
	}

	public void changeServicePrice(int code, int price) {
		getRoomService().changeRoomPrice(code, price);
	}

	public ClientService getClientService() {
		return clientService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public List<Client> getAllClients() {
		return getClientService().getAllClients();
	}
}

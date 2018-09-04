package com.senla.hotel.facade;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.di.DependencyInjection;
import com.senla.hotel.comparator.OrderSortByClientName;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.comparator.RoomSortByCapacity;
import com.senla.hotel.comparator.RoomSortByPrice;
import com.senla.hotel.comparator.RoomSortByStar;
import com.senla.hotel.comparator.ServiceSortByPrice;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.facade.api.IHotel;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.property.HotelProperty;
import com.senla.hotel.services.api.IClientService;
import com.senla.hotel.services.api.IOrderService;
import com.senla.hotel.services.api.IRoomService;
import com.senla.hotel.services.api.IServiceService;
import com.senla.util.ModelSerializer;

public class Hotel implements IHotel {

	private static final String ERROR_CLIENTS_ADD = "Error adding clients.";
	private static final String ERROR_ROOMS_ADD = "Error adding rooms.";
	private static final String ERROR_SERVICES_ADD = "Error adding services.";
	private static final String ERROR_ORDERS_ADD = "Error adding orders.";

	private IClientService clientService;
	private IRoomService roomService;
	private IServiceService serviceService;
	private IOrderService orderService;

	private static IHotel hotel;

	private static final Logger logger = Logger.getLogger(Hotel.class);

	public Hotel() {
		super();
		this.clientService = DependencyInjection.getInstance().getInterfacePair(IClientService.class);
		this.roomService = DependencyInjection.getInstance().getInterfacePair(IRoomService.class);
		this.serviceService = DependencyInjection.getInstance().getInterfacePair(IServiceService.class);
		this.orderService = DependencyInjection.getInstance().getInterfacePair(IOrderService.class);
	}

	public static IHotel getInstance() {
		if (hotel == null) {
			hotel = DependencyInjection.getInstance().getInterfacePair(IHotel.class);
		}
		return hotel;
	}

	private int addAll(ModelSerializer serializer) {
		int result = 0b0000;

		result = result | (clientService.addAll(serializer.getClients()) ? 0b1000 : 0b0000);
		result = result | (roomService.addAll(serializer.getRooms()) ? 0b0100 : 0b0000);
		result = result | (serviceService.addAll(serializer.getServices()) ? 0b0010 : 0b0000);
		result = result | (orderService.addAll(serializer.getOrders()) ? 0b0001 : 0b0000);

		return result;
	}

	@Override
	public boolean load() {
		boolean result = false;
		String filePath = HotelProperty.getInstance().getRawFilePath();

		ModelSerializer serializer = new ModelSerializer();

		if (serializer.deserialize(filePath + "hotel.raw")) {

			int addResult = addAll(serializer);
			if (addResult == 0b1111) {
				result = true;
			} else {
				if ((addResult ^ 0b0111) != 0b0000) {
					logger.error(ERROR_CLIENTS_ADD);
				}
				if ((addResult ^ 0b1011) != 0b0000) {
					logger.error(ERROR_ROOMS_ADD);
				}
				if ((addResult ^ 0b1101) != 0b0000) {
					logger.error(ERROR_SERVICES_ADD);
				}
				if ((addResult ^ 0b1110) != 0b0000) {
					logger.error(ERROR_ORDERS_ADD);
				}
			}

		}
		return result;
	}

	public boolean save() {
		boolean result = true;
		String filePath = HotelProperty.getInstance().getRawFilePath();
		ModelSerializer serializer = new ModelSerializer();
		try {
			serializer.setClients(clientService.getClients());
			serializer.setRooms(roomService.getRooms());
			serializer.setServices(serviceService.getServices());
			serializer.setOrders(orderService.getOrders());

			serializer.serialize(filePath + "hotel.raw");
		} catch (IOException ex) {
			logger.error(ex);
			result = false;
		} catch (Exception ex) {
			logger.error(ex);
			result = false;
		}
		return result;
	}

	public List<Room> getAllRoomsSortByPrice() {
		try {
			return roomService.getAllRooms(new RoomSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getAllRoomsSortByCapacity() {
		try {
			return roomService.getAllRooms(new RoomSortByCapacity());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getAllRoomsSortByStar() {
		try {
			return roomService.getAllRooms(new RoomSortByStar());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsSortByPrice() {
		try {
			return roomService.getFreeRooms(new RoomSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public List<Room> getFreeRoomsSortByCapacity() {
		try {
			return roomService.getFreeRooms(new RoomSortByCapacity());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsSortByStar() {
		try {
			return roomService.getFreeRooms(new RoomSortByStar());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsByDateSortByPrice(Date date) {
		try {
			return roomService.getFreeRooms(date, new RoomSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsByDateSortByCapacity(Date date) {
		try {
			return roomService.getFreeRooms(date, new RoomSortByCapacity());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsByDateSortByStar(Date date) {
		try {
			return roomService.getFreeRooms(date, new RoomSortByStar());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getNumberOfFreeRooms() {
		try {
			return roomService.getNumberOfFreeRooms();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getNumberOfClients() {
		try {
			return clientService.getNumberOfClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getAllOrders() {
		return orderService.getOrders();
	}

	public List<Order> getActiveOrdersSortByName() {
		try {
			return orderService.getActiveOrders(new OrderSortByClientName());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getActiveOrdersSortByFinishDate() {
		try {
			return orderService.getActiveOrders(new OrderSortByFinishDate());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getOrderPrice(int orderNum) {
		try {
			return orderService.getOrderPrice(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getLastOrdersByRoom(int roomNum) {
		try {
			return orderService.getLastOrdersByRoom(roomNum, HotelProperty.getInstance().getLastVisibleOrders(),
					new OrderSortByFinishDate());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Service> getOrderServices(int orderNum) {
		try {
			return orderService.getOrderServices(orderNum, new ServiceSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Service> getAllServicesSortByPrice() {
		try {
			return serviceService.getAllServices(new ServiceSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean addClient(String name) {
		try {
			return clientService.add(new Client(name));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		try {
			return roomService.addRoom(number, capacity, star, status, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean addService(int code, String name, int price) {
		try {
			return serviceService.addService(code, name, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	public boolean addOrder(Order order) {
		try {
			return orderService.add(order);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {
		return orderService.addOrder(num, clientName, roomNum, startDate, finishDate);
	}

	public boolean orderRoom(int orderNum, String clientName, int roomNum, Date dateStart, Date dateFinish) {
		return orderService.orderRoom(orderNum, roomNum, clientName, dateStart, dateFinish);
	}

	public boolean addOrderService(int orderNum, int serviceCode) {
		try {
			return orderService.addOrderService(orderNum, serviceCode);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean freeRoom(int orderNum) {
		try {
			return orderService.freeRoom(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean changeRoomStatus(int roomNum, RoomStatus roomStatus) {
		try {
			if (HotelProperty.getInstance().isAbleChangeRoomStatus()) {
				return roomService.changeRoomStatus(roomNum, roomStatus);
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean changeRoomPrice(int roomNum, int newPrice) {
		try {
			return roomService.changeRoomPrice(roomNum, newPrice);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean changeServicePrice(int code, int price) {
		try {
			return serviceService.changeServicePrice(code, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public List<Client> getAllClients() {
		try {
			return clientService.getClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Order cloneOrder(int orderNum) {
		try {
			return orderService.cloneOrder(orderNum);
		} catch (CloneNotSupportedException e) {
			logger.error(e);
			return null;
		}
	}

	public Order getOrderByNum(int orderNum) {
		try {
			return orderService.getOrderByNum(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Client getClientByName(String name) {
		return clientService.getClientByName(name);
	}

	public Room getRoomByNum(int roomNum) {
		try {
			return roomService.getRoomByNum(roomNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Service getServiceByCode(int code) {
		try {
			return serviceService.getServiceByCode(code);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean exportClientCSV(String name, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "client_" + name + ".csv" : fileName);
			return clientService.exportClientCSV(name, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean exportOrderCSV(int orderNum, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "order_" + orderNum + ".csv" : fileName);
			return orderService.exportOrderCSV(orderNum, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean exportRoomCSV(int roomNum, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "room_" + roomNum + ".csv" : fileName);
			return roomService.exportRoomCSV(roomNum, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean exportServiceCSV(int code, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "service_" + code + ".csv" : fileName);
			return serviceService.exportServiceCSV(code, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean importClientsCSV(String fileName) {
		try {
			return clientService.importClientsCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (NullPointerException ex) {
			logger.error(ex);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean importOrdersCSV(String fileName) {
		try {
			return orderService.importOrdersCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (NullPointerException ex) {
			logger.error(ex);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean importRoomsCSV(String fileName) {
		try {
			return roomService.importRoomsCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (NullPointerException ex) {
			logger.error(ex);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean importServicesCSV(String fileName) {
		try {
			return serviceService.importServicesCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (NullPointerException ex) {
			logger.error(ex);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean exportCsv() {
		boolean result = false;
		result = clientService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
		if (result) {
			result = serviceService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = roomService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = orderService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		return result;
	}

	public boolean importCsv() {
		boolean result = false;
		result = clientService.importCsv(HotelProperty.getInstance().getCsvFilePath());
		if (result) {
			result = orderService.importCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = roomService.importCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = serviceService.importCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		return result;
	}

}

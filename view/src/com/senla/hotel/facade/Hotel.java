package com.senla.hotel.facade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.di.DependencyInjection;
import com.senla.hotel.comparator.OrderSortByClientName;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.EnumServiceSort;
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

	private boolean addAll(ModelSerializer serializer) {
		boolean result;
		try {
			result = clientService.addAll(serializer.getClients());
			result = result && roomService.addAll(serializer.getRooms());
			result = result && serviceService.addAll(serializer.getServices());
			result = result && orderService.addAll(serializer.getOrders());
		} catch (SQLException e) {
			logger.error(e);
			result = false;
		}
		return result;
	}

	@Override
	public boolean load() {
		boolean result = false;
		String filePath = HotelProperty.getInstance().getRawFilePath();

		ModelSerializer serializer = new ModelSerializer();

		if (serializer.deserialize(filePath + "hotel.raw")) {
			result = addAll(serializer);
		}
		return result;
	}

	@Override
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
		} catch (SQLException ex) {
			logger.error(ex);
			result = false;
		}
		return result;
	}

	@Override
	public List<Room> getAllRoomsSortByPrice() {
		try {
			return roomService.getAllRooms(EnumRoomSort.PRICE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getAllRoomsSortByCapacity() {
		try {
			return roomService.getAllRooms(EnumRoomSort.CAPACITY);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getAllRoomsSortByStar() {
		try {
			return roomService.getAllRooms(EnumRoomSort.STAR);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getFreeRoomsSortByPrice() {
		try {
//TODO delete comparators
			return roomService.getFreeRooms(EnumRoomSort.PRICE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	@Override
	public List<Room> getFreeRoomsSortByCapacity() {
		try {
			return roomService.getFreeRooms(EnumRoomSort.CAPACITY);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getFreeRoomsSortByStar() {
		try {
			return roomService.getFreeRooms(EnumRoomSort.STAR);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getFreeRoomsByDateSortByPrice(Date date) {
		try {
			return roomService.getFreeRooms(date, EnumRoomSort.PRICE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getFreeRoomsByDateSortByCapacity(Date date) {
		try {
			return roomService.getFreeRooms(date, EnumRoomSort.CAPACITY);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getFreeRoomsByDateSortByStar(Date date) {
		try {
			return roomService.getFreeRooms(date, EnumRoomSort.STAR);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Integer getNumberOfFreeRooms() {
		try {
			return roomService.getNumberOfFreeRooms();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Integer getNumberOfClients() {
		try {
			return clientService.getNumberOfClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Order> getAllOrders() {
		try {
			return orderService.getOrders();
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Order> getActiveOrdersSortByName() {
		try {
			return orderService.getActiveOrders(new OrderSortByClientName());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Order> getActiveOrdersSortByFinishDate() {
		try {
			return orderService.getActiveOrders(new OrderSortByFinishDate());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Integer getOrderPrice(int orderId) {
		try {
			return orderService.getOrderPrice(orderId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Order> getLastOrdersByRoom(int roomId) {
		try {
			return orderService.getLastOrdersByRoom(roomId, HotelProperty.getInstance().getLastVisibleOrders(),
					new OrderSortByFinishDate());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Service> getOrderServices(int orderId) {
		try {
			return orderService.getOrderServices(orderId, EnumServiceSort.ID);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Service> getAllServicesSortByPrice() {
		try {
			return serviceService.getAllServices(EnumServiceSort.PRICE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean addClient(String name) {
		try {
			return clientService.add(new Client(name));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	@Override
	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		try {
			return roomService.addRoom(number, capacity, star, status, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean addService(int code, String name, int price) {
		try {
			return serviceService.addService(code, name, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	@Override
	public boolean addOrder(Order order) {
		try {
			return orderService.add(order);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean addOrder(int num, int clientId, int roomId, Date startDate, Date finishDate) {
		try {
			return orderService.addOrder(num, clientId, roomId, startDate, finishDate);
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean orderRoom(int orderNum, int roomId, int clientId, Date dateStart, Date dateFinish) {
		try {
			return orderService.orderRoom(orderNum, roomId, clientId, dateStart, dateFinish);
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean addOrderService(int orderNum, int serviceCode) {
		try {
			return orderService.addOrderService(orderNum, serviceCode);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean freeRoom(int orderNum) {
		try {
			return orderService.freeRoom(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
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

	@Override
	public boolean changeRoomPrice(int roomNum, int newPrice) {
		try {
			return roomService.changeRoomPrice(roomNum, newPrice);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean changeServicePrice(int code, int price) {
		try {
			return serviceService.changeServicePrice(code, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public List<Client> getAllClients() {
		try {
			return clientService.getClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Order cloneOrder(int orderNum) {
		try {
			return orderService.cloneOrder(orderNum);
		} catch (CloneNotSupportedException e) {
			logger.error(e);
			return null;
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Order getOrderById(int orderId) {
		try {
			return orderService.getOrderById(orderId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

//	public Client getClientByName(String name) {
//	return clientService.getClientByName(name);
//  }

	public Client getClientById(int clientId) {
		try {
			return clientService.getClientById(clientId);
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Room getRoomById(int roomId) {
		try {
			return roomService.getRoomById(roomId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Service getServiceById(int serviceId) {
		try {
			return serviceService.getServiceById(serviceId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean exportClientCSV(int id, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "client_" + id + ".csv" : fileName);
			return clientService.exportClientCSV(id, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public boolean exportCsv() {
		try {
			clientService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
			serviceService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
			roomService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
			orderService.exportCsv(HotelProperty.getInstance().getCsvFilePath());
			return true;
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean importCsv() {
		try {
			System.out.println("clientService>" + clientService);

			clientService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			orderService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			roomService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			serviceService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			return true;
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}
	}

}

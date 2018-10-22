package com.senla.hotel.facade;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.di.DependencyInjection;
import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.facade.api.IHotel;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.property.HotelProperty;
import com.senla.hotel.services.api.IClientService;
import com.senla.hotel.services.api.IConnectorService;
import com.senla.hotel.services.api.IOrderService;
import com.senla.hotel.services.api.IRoomService;
import com.senla.hotel.services.api.IServiceService;

public class Hotel implements IHotel {

	private IClientService clientService;
	private IRoomService roomService;
	private IServiceService serviceService;
	private IOrderService orderService;
	private IConnectorService connectorService;

	private static IHotel hotel;

	private static final Logger logger = Logger.getLogger(Hotel.class);

	public Hotel() {
		super();
		this.clientService = DependencyInjection.getInstance().getInterfacePair(IClientService.class);
		this.roomService = DependencyInjection.getInstance().getInterfacePair(IRoomService.class);
		this.serviceService = DependencyInjection.getInstance().getInterfacePair(IServiceService.class);
		this.orderService = DependencyInjection.getInstance().getInterfacePair(IOrderService.class);
		this.connectorService = DependencyInjection.getInstance().getInterfacePair(IConnectorService.class);
	}

	public static IHotel getInstance() {
		if (hotel == null) {
			hotel = DependencyInjection.getInstance().getInterfacePair(IHotel.class);
		}
		return hotel;
	}

	@Override
	public List<Room> getAllRoomsSortByPrice() {
		try {
			return roomService.getRooms(EnumRoomSort.PRICE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getAllRoomsSortByCapacity() {
		try {
			return roomService.getRooms(EnumRoomSort.CAPACITY);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getAllRoomsSortByStar() {
		try {
			return roomService.getRooms(EnumRoomSort.STAR);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Room> getFreeRoomsSortByPrice() {
		try {
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
	public Long getNumberOfFreeRooms() {
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
		return orderService.getOrders();
	}

	@Override
	public List<Order> getActiveOrdersSortByName() {
		try {
			return orderService.getActiveOrders(EnumOrderSort.CLIENT_NAME);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Order> getActiveOrdersSortByFinishDate() {
		try {
			return orderService.getActiveOrders(EnumOrderSort.FINISH_DATE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Integer getOrderPrice(Order order) {
		try {
			return orderService.getOrderPrice(order);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Order> getLastOrdersByRoom(Room room) {
		try {
			return orderService.getLastOrdersByRoom(room, HotelProperty.getInstance().getLastVisibleOrders(),
					EnumOrderSort.FINISH_DATE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Service> getOrderServices(Order order) {
		try {
			return orderService.getOrderServices(order, EnumServiceSort.ID);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Service> getAllServicesSortByPrice() {
		try {
			return serviceService.getServices(EnumServiceSort.PRICE);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void addClient(Client client) {
		clientService.add(client);
	}

	@Override
	public void addRoom(Room room) {
		try {
			roomService.add(room);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void addService(int code, String name, int price) {
		try {
			serviceService.addService(new Service(code, name, price));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Override
	public void addOrder(Order order) {
		try {
			orderService.add(order);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void addOrderService(Order order, Service service) {
		try {
			orderService.addOrderService(order, service);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void freeRoom(int orderId) {
		try {
			orderService.freeRoom(orderId);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void changeRoomStatus(int roomId, RoomStatus roomStatus) {
		try {
			if (HotelProperty.getInstance().isAbleChangeRoomStatus()) {
				roomService.changeRoomStatus(roomId, roomStatus);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void changeRoomPrice(int roomId, int newPrice) {
		try {
			roomService.changeRoomPrice(roomId, newPrice);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void changeServicePrice(int serviceId, int price) {
		try {
			serviceService.changeServicePrice(serviceId, price);
		} catch (Exception e) {
			logger.error(e);
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
	public Order cloneOrder(Order order) {
		try {
			return orderService.cloneOrder(order);
		} catch (CloneNotSupportedException e) {
			logger.error(e);
			return null;
		} catch (Exception e) {
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

	public Client getClientById(int clientId) {
		try {
			return clientService.getClientById(clientId);
		} catch (Exception e) {
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
	public boolean exportOrderCSV(int orderId, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "order_" + orderId + ".csv" : fileName);
			return orderService.exportOrderCSV(orderId, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean exportRoomCSV(int roomId, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "room_" + roomId + ".csv" : fileName);
			return roomService.exportRoomCSV(roomId, filePath);
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean exportServiceCSV(int serviceId, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "service_" + serviceId + ".csv" : fileName);
			return serviceService.exportServiceCSV(serviceId, filePath);
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
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean importCsv() {
		try {
			clientService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			orderService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			roomService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			serviceService.importCsv(HotelProperty.getInstance().getCsvFilePath());
			return true;
		} catch (IOException e) {
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public void close() {
		try {
			connectorService.CloseConnection();
		} catch (Exception e) {
			logger.error(e);
		}
	}

}

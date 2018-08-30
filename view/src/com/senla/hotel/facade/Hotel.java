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

	private IClientService getClientService() {
		return clientService;
	}

	private IRoomService getRoomService() {
		return roomService;
	}

	private IServiceService getServiceService() {
		return serviceService;
	}

	private IOrderService getOrderService() {
		return orderService;
	}

	public boolean load() {
		boolean result = true;
		String filePath = HotelProperty.getInstance().getRawFilePath();
		ModelSerializer serializer = new ModelSerializer();
		try {
			result = serializer.deserialize(filePath + "hotel.raw");

			getClientService().addAll(serializer.getClients());

			getRoomService().addAll(serializer.getRooms());

			getServiceService().addAll(serializer.getServices());

			getOrderService().addAll(serializer.getOrders());

		} catch (IOException e) {
			logger.error(e);
			result = false;
		} catch (ClassNotFoundException e) {
			logger.error(e);
			result = false;
		}
		return result;
	}

	public boolean save() {
		boolean result = true;
		String filePath = HotelProperty.getInstance().getRawFilePath();
		ModelSerializer serializer = new ModelSerializer();
		try {
			serializer.setClients(getClientService().getClients());
			serializer.setRooms(getRoomService().getRooms());
			serializer.setServices(getServiceService().getServices());
			serializer.setOrders(getOrderService().getOrders());

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
			return getRoomService().getAllRooms(new RoomSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getAllRoomsSortByCapacity() {
		try {
			return getRoomService().getAllRooms(new RoomSortByCapacity());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getAllRoomsSortByStar() {
		try {
			return getRoomService().getAllRooms(new RoomSortByStar());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsSortByPrice() {
		try {
			return getRoomService().getFreeRooms(new RoomSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public List<Room> getFreeRoomsSortByCapacity() {
		try {
			return getRoomService().getFreeRooms(new RoomSortByCapacity());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsSortByStar() {
		try {
			return getRoomService().getFreeRooms(new RoomSortByStar());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsByDateSortByPrice(Date date) {
		try {
			return getRoomService().getFreeRooms(date, new RoomSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsByDateSortByCapacity(Date date) {
		try {
			return getRoomService().getFreeRooms(date, new RoomSortByCapacity());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Room> getFreeRoomsByDateSortByStar(Date date) {
		try {
			return getRoomService().getFreeRooms(date, new RoomSortByStar());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getNumberOfFreeRooms() {
		try {
			return getRoomService().getNumberOfFreeRooms();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getNumberOfClients() {
		try {
			return getClientService().getNumberOfClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getAllOrders() {
		return getOrderService().getOrders();
	}

	public List<Order> getActiveOrdersSortByName() {
		try {
			return getOrderService().getActiveOrders(new OrderSortByClientName());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getActiveOrdersSortByFinishDate() {
		try {
			return getOrderService().getActiveOrders(new OrderSortByFinishDate());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getOrderPrice(int orderNum) {
		try {
			return getOrderService().getOrderPrice(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getLastOrdersByRoom(int roomNum) {
		try {
			return getOrderService().getLastOrdersByRoom(roomNum, HotelProperty.getInstance().getLastVisibleOrders(),
					new OrderSortByFinishDate());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Service> getOrderServices(int orderNum) {
		try {
			return getOrderService().getOrderServices(orderNum, new ServiceSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Service> getAllServicesSortByPrice() {
		try {
			return getServiceService().getAllServices(new ServiceSortByPrice());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean addClient(String name) {
		try {
			return getClientService().add(new Client(name));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		try {
			return getRoomService().addRoom(number, capacity, star, status, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean addService(int code, String name, int price) {
		try {
			return getServiceService().addService(code, name, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	public boolean addOrder(Order order) {
		try {
			return getOrderService().add(order);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {
		return getOrderService().addOrder(num, clientName, roomNum, startDate, finishDate);
	}

	public boolean orderRoom(int orderNum, String clientName, int roomNum, Date dateStart, Date dateFinish) {
		return getOrderService().orderRoom(orderNum, roomNum, clientName, dateStart, dateFinish);
	}

	public boolean addOrderService(int orderNum, int serviceCode) {
		try {
			return getOrderService().addOrderService(orderNum, serviceCode);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean freeRoom(int orderNum) {
		try {
			return getOrderService().freeRoom(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean changeRoomStatus(int roomNum, RoomStatus roomStatus) {
		try {
			if (HotelProperty.getInstance().isAbleChangeRoomStatus()) {
				return getRoomService().changeRoomStatus(roomNum, roomStatus);
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
			return getRoomService().changeRoomPrice(roomNum, newPrice);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean changeServicePrice(int code, int price) {
		try {
			return getServiceService().changeServicePrice(code, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public List<Client> getAllClients() {
		try {
			return getClientService().getClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Order cloneOrder(int orderNum) {
		try {
			return getOrderService().cloneOrder(orderNum);
		} catch (CloneNotSupportedException e) {
			logger.error(e);
			return null;
		}
	}

	public Order getOrderByNum(int orderNum) {
		try {
			return getOrderService().getOrderByNum(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Client getClientByName(String name) {
		return getClientService().getClientByName(name);
	}

	public Room getRoomByNum(int roomNum) {
		try {
			return getRoomService().getRoomByNum(roomNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Service getServiceByCode(int code) {
		try {
			return getServiceService().getServiceByCode(code);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean exportClientCSV(String name, String fileName) {
		try {
			String filePath = HotelProperty.getInstance().getCsvFilePath()
					+ (fileName.equals("") ? "client_" + name + ".csv" : fileName);
			return getClientService().exportClientCSV(name, filePath);
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
			return getOrderService().exportOrderCSV(orderNum, filePath);
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
			return getRoomService().exportRoomCSV(roomNum, filePath);
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
			return getServiceService().exportServiceCSV(code, filePath);
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
			return getClientService().importClientsCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
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
			return getOrderService().importOrdersCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
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
			return getRoomService().importRoomsCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
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
			return getServiceService().importServicesCSV(HotelProperty.getInstance().getCsvFilePath() + fileName);
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
		result = getClientService().exportCsv(HotelProperty.getInstance().getCsvFilePath());
		if (result) {
			result = getServiceService().exportCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = getRoomService().exportCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = getOrderService().exportCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		return result;
	}

	public boolean importCsv() {
		boolean result = false;
		result = getClientService().importCsv(HotelProperty.getInstance().getCsvFilePath());
		if (result) {
			result = getOrderService().importCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = getRoomService().importCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		if (result) {
			result = getServiceService().importCsv(HotelProperty.getInstance().getCsvFilePath());
		}
		return result;
	}

}

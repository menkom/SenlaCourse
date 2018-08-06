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

	public Boolean load(String filePath) {
		Boolean result = true;
		try {
			result = getClientService().loadFromFile(filePath);
			if (result) {
				result = getRoomService().loadFromFile(filePath);
			}
			if (result) {
				result = getServiceService().loadFromFile(filePath);
			}
			if (result) {
				result = getOrderService().loadFromFile(filePath);
			}
		} catch (NumberFormatException | IOException | ParseException ex) {
			logger.error(ex);
			result = false;
		}
		return result;
	}

	public Boolean save(String filePath) {
		Boolean result = true;
		try {
			result = getClientService().saveToFile(filePath);
			if (result) {
				result = getRoomService().saveToFile(filePath);
			}
			if (result) {
				result = getServiceService().saveToFile(filePath);
			}
			if (result) {
			}
			result = getOrderService().saveToFile(filePath);
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

	public Room getRoomByNum(int roomNum) {
		try {
			return getRoomService().getRoomByNum(roomNum);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Order> getLastOrdersByRoom(int roomNum, int maxOrders) {
		try {
			return getOrderService().getLastOrdersByRoom(roomNum, maxOrders, new OrderSortByFinishDate());
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

	public Boolean addClient(String name) {
		try {
			return getClientService().add(new Client(name));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	public Boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		try {
			return getRoomService().addRoom(number, capacity, star, status, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public Boolean addService(int code, String name, int price) {
		try {
			return getServiceService().addService(code, name, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	public Boolean addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {
		try {
			return getOrderService().addOrder(num, clientName, roomNum, startDate, finishDate);
		} catch (NoEntryException e) {
			logger.error(e);
			return false;
		}
	}

	public Boolean orderRoom(Integer orderNum, String clientName, Integer roomNum, Date dateStart, Date dateFinish) {
		try {
			return getOrderService().orderRoom(orderNum, roomNum, clientName, dateStart, dateFinish);
		} catch (NoEntryException e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
			return false;
		}
	}

	public Boolean addOrderService(int orderNum, int serviceCode) {
		try {
			return getOrderService().addOrderService(orderNum, serviceCode);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public Boolean freeRoom(int orderNum) {
		try {
			return getOrderService().freeRoom(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public Boolean changeRoomStatus(int roomNum, RoomStatus roomStatus) {
		try {
			return getRoomService().changeRoomStatus(roomNum, roomStatus);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public Boolean changeRoomPrice(int roomNum, int newPrice) {
		try {
			return getRoomService().changeRoomPrice(roomNum, newPrice);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public Boolean changeServicePrice(int code, int price) {
		try {
			return getServiceService().changeServicePrice(code, price);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
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
		try {
			return getClientService().getAllClients();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}

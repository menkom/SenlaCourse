package com.senla.hotel.facade.api;

import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public interface IHotel {

	public boolean load();

	public boolean save();

	public List<Room> getAllRoomsSortByPrice();

	public List<Room> getAllRoomsSortByCapacity();

	public List<Room> getAllRoomsSortByStar();

	public List<Room> getFreeRoomsSortByPrice();

	public List<Room> getFreeRoomsSortByCapacity();

	public List<Room> getFreeRoomsSortByStar();

	public List<Room> getFreeRoomsByDateSortByPrice(Date date);

	public List<Room> getFreeRoomsByDateSortByCapacity(Date date);

	public List<Room> getFreeRoomsByDateSortByStar(Date date);

	public Integer getNumberOfFreeRooms();

	public Integer getNumberOfClients();

	public List<Order> getAllOrders();

	public List<Order> getActiveOrdersSortByName();

	public List<Order> getActiveOrdersSortByFinishDate();

	public Integer getOrderPrice(int orderNum);

	public List<Order> getLastOrdersByRoom(int roomNum);

	public List<Service> getOrderServices(int orderNum);

	public List<Service> getAllServicesSortByPrice();

	public boolean addClient(String name);

	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price);

	public boolean addService(int code, String name, int price);

	public boolean addOrder(Order order);

	public boolean addOrder(int num, int clientId, int roomId, Date startDate, Date finishDate);

	public boolean orderRoom(int orderNum, int roomId, int clientId, Date dateStart, Date dateFinish);

	public boolean addOrderService(int orderId, int serviceCode);

	public boolean freeRoom(int orderNum);

	public boolean changeRoomStatus(int roomId, RoomStatus roomStatus);

	public boolean changeRoomPrice(int roomId, int newPrice);

	public boolean changeServicePrice(int ServiceId, int price);

	public List<Client> getAllClients();

	public Order cloneOrder(int orderId);

	public Order getOrderById(int orderId);

	public Client getClientById(int clientId);

	Room getRoomById(int roomId);

	Service getServiceById(int serviceId);

	public boolean exportClientCSV(int id, String fileName);

	public boolean exportOrderCSV(int orderId, String fileName);

	public boolean exportRoomCSV(int roomId, String fileName);

	public boolean exportServiceCSV(int serviceId, String fileName);

	public boolean importClientsCSV(String fileName);

	public boolean importOrdersCSV(String fileName);

	public boolean importRoomsCSV(String fileName);

	public boolean importServicesCSV(String fileName);

	public boolean exportCsv();

	public boolean importCsv();

}

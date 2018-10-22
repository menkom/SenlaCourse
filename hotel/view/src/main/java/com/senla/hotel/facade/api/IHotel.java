package com.senla.hotel.facade.api;

import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public interface IHotel {

	List<Room> getAllRoomsSortByPrice();

	List<Room> getAllRoomsSortByCapacity();

	List<Room> getAllRoomsSortByStar();

	List<Room> getFreeRoomsSortByPrice();

	List<Room> getFreeRoomsSortByCapacity();

	List<Room> getFreeRoomsSortByStar();

	List<Room> getFreeRoomsByDateSortByPrice(Date date);

	List<Room> getFreeRoomsByDateSortByCapacity(Date date);

	List<Room> getFreeRoomsByDateSortByStar(Date date);

	Long getNumberOfFreeRooms();

	Integer getNumberOfClients();

	List<Order> getAllOrders();

	List<Order> getActiveOrdersSortByName();

	List<Order> getActiveOrdersSortByFinishDate();

	Integer getOrderPrice(Order order);

	List<Order> getLastOrdersByRoom(Room room);

	List<Service> getOrderServices(Order order);

	List<Service> getAllServicesSortByPrice();

	void addClient(Client client);

	void addRoom(Room room);

	void addService(int code, String name, int price);

	void addOrder(Order order);

	void addOrderService(Order order, Service service);

	void freeRoom(int orderId);

	void changeRoomStatus(int roomId, RoomStatus roomStatus);

	void changeRoomPrice(int roomId, int newPrice);

	void changeServicePrice(int ServiceId, int price);

	List<Client> getAllClients();

	Order cloneOrder(Order order);

	Order getOrderById(int orderId);

	Client getClientById(int clientId);

	Room getRoomById(int roomId);

	Service getServiceById(int serviceId);

	boolean exportClientCSV(int id, String fileName);

	boolean exportOrderCSV(int orderId, String fileName);

	boolean exportRoomCSV(int roomId, String fileName);

	boolean exportServiceCSV(int serviceId, String fileName);

	boolean importClientsCSV(String fileName);

	boolean importOrdersCSV(String fileName);

	boolean importRoomsCSV(String fileName);

	boolean importServicesCSV(String fileName);

	boolean exportCsv();

	boolean importCsv();

	void close();

}

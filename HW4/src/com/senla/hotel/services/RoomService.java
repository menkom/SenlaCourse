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
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.util.ArrayOperator;
import com.senla.util.FileOperator;

public class RoomService implements IService {

	private ClientRepository clientRepository;
	private RoomRepository roomRepository;
	private ServiceRepository serviceRepository;
	private OrderRepository orderRepository;

	public RoomService(ClientRepository clientRepository, RoomRepository roomRepository,
			ServiceRepository serviceRepository, OrderRepository orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;
	}

	public String getFileName() {
		return "room.db";
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
		getRoomRepository().add(element);
	}

	public void addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		Room element = new Room(number, capacity, star, status, price);
		getRepository().add(element);
	}

	public Room[] getAllRooms() {
		return (Room[]) getRoomRepository().getRepository();
	}

	public int getNumberOfFreeRooms() {
		int result = 0;
		for (Room room : (Room[]) getRoomRepository().getRepository()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result++;
			}
		}
		return result;
	}

	public Room[] getFreeRooms() {
		// This method can be implemented as not fixed length array (in the way
		// getFreeRooms(Date))
		Room[] result = new Room[getNumberOfFreeRooms()];

		for (Room room : (Room[]) getRoomRepository().getRepository()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result[ArrayOperator.getFreeIndex(result)] = room;
			}
		}
		return result;
	}

	public Room getRoomByNum(int number) {
		return getRoomRepository().getRoomByNum(number);
	}

	public String getRoomInfo(int number) {
		return getRoomByNum(number).toString();
	}

	public void changeRoomStatus(int number, RoomStatus roomStatus) {
		Room room = getRoomByNum(number);
		if (room != null) {
			room.setStatus(roomStatus);
		}
	}

	public void changeRoomPrice(int number, int price) {
		Room room = getRoomByNum(number);
		if (room != null) {
			room.setPrice(price);
		}
	}

	public Room[] getFreeRooms(Date date) {

		Room[] result = new Room[0];

		// We need to get a list of occupied rooms and exclude this from all rooms
		//
		Room[] resultExclude = new Room[0];

		for (Order order : (Order[]) getOrderRepository().getRepository()) {
			if ((date.after(order.getStartDate())
					&& ((date.before(order.getFinishDate()) || order.getFinishDate() == null)))) {
				resultExclude = (Room[]) ArrayOperator.add(resultExclude, order.getRoom());
			}
		}

		for (Room room : (Room[]) getRoomRepository().getRepository()) {
			if (!isRoomInArray(room, resultExclude)) {
				result = (Room[]) ArrayOperator.add(result, room);
			}
		}

		return result;
	}

	public boolean isRoomInArray(Room room, Room[] rooms) {
		boolean result = false;
		for (Room forRoom : rooms) {
			if (room.getNumber() == forRoom.getNumber()) {
				result = true;
				break;
			}
		}
		return result;
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		RoomService service = new FileOperator().getRoomService(dbPath + getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setRepository(((RoomRepository) service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) {
		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

	@Override
	public IBaseRepository getRepository() {
		return getRoomRepository();
	}

	@Override
	public String[] getStringToSave() {
		return getRepository().toStringArray();
	}
}

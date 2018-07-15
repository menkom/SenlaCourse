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
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.util.ArrayOperator;
import com.senla.util.FileOperator;

public class RoomService implements IService {

	private ClientArray clientRepository;
	private RoomArray roomRepository;
	private ServiceArray serviceRepository;
	private OrderArray orderRepository;

	public RoomService(ClientArray clientRepository, RoomArray roomRepository, ServiceArray serviceRepository,
			OrderArray orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;
	}

	public String getFileName() {
		return "room.db";
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
		getRoomRepository().add(element);
	}

	public void addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		Room element = new Room(number, capacity, star, status, price);
		getRepository().add(element);
	}

	public Room[] getAllRooms() {
		return (Room[]) getRoomRepository().getArray();
	}

	public int getNumberOfFreeRooms() {
		int result = 0;
		for (Room room : (Room[]) getRoomRepository().getArray()) {
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

		for (Room room : (Room[]) getRoomRepository().getArray()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result[new ArrayOperator().getFreeIndex(result)] = room;
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

		for (Order order : (Order[]) getOrderRepository().getArray()) {
			if ((date.after(order.getStartDate())
					&& ((date.before(order.getFinishDate()) || order.getFinishDate() == null)))) {

				resultExclude = (Room[]) new ArrayOperator().add(resultExclude, order.getRoom());
			}
		}

		for (Room room : (Room[]) getRoomRepository().getArray()) {
			if (!isRoomInArray(room, resultExclude)) {
				result = (Room[]) new ArrayOperator().add(result, room);
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

	public void loadFromDB() throws IOException, NumberFormatException, ParseException {
		RoomService service = new FileOperator().getRoomService(getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setArray(((RoomArray) service.getRepository()).getArray());
	}

	public void saveToDB() {
		new FileOperator().saveToDB(getFileName(), getStringToSave());
	}

	@Override
	public IBaseArray getRepository() {
		return getRoomRepository();
	}

	@Override
	public String[] getStringToSave() {
		return getRepository().toStringArray();
	}
}

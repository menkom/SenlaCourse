package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.RoomRepository;
//import com.senla.util.FileOperator;

public class RoomService implements IService {

	private RoomRepository roomRepository;
	private static RoomService roomService;

	private RoomService() {
		super();
		this.roomRepository = RoomRepository.getInstance();
	}

	public static RoomService getInstance() {
		if (roomService == null) {
			roomService = new RoomService();
		}
		return roomService;
	}

	public String getFileName() {
		return "room.db";
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public void add(Room element) {
		getRoomRepository().add(element);
	}

	public void addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		Room element = new Room(number, capacity, star, status, price);
		add(element);
	}

	public List<Room> getAllRooms() {
		return getRoomRepository().getRooms();
	}

	public int getNumberOfFreeRooms() {
		int result = 0;
		for (Room room : getRoomRepository().getRooms()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result++;
			}
		}
		return result;
	}

	public List<Room> getFreeRooms() {
		List<Room> result = new ArrayList<>();

		for (Room room : getRoomRepository().getRooms()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result.add(room);
			}
		}
		return result;
	}

	public Room getRoomByNum(int number) {
		return getRoomRepository().getRoomByNum(number);
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

	public List<Room> getFreeRooms(Date date) {

		List<Room> result = new ArrayList<>();

		// We need to get a list of occupied rooms and exclude this from all rooms
		//
		List<Room> resultExclude = new ArrayList<>();

		for (Order order : OrderService.getInstance().getOrderRepository().getOrders()) {
			if (order != null) {
				if ((date.after(order.getStartDate())
						&& ((date.before(order.getFinishDate()) || order.getFinishDate() == null)))) {
					resultExclude.add(order.getRoom());
				}
			}
		}

		for (Room room : getRoomRepository().getRooms()) {
			if (room != null) {
				if (!isRoomInArray(room, resultExclude)) {
					result.add(room);
				}
			}
		}

		return result;
	}

	public boolean isRoomInArray(Room room, List<Room> rooms) {
		boolean result = false;
		for (Room forRoom : rooms) {
			if (forRoom != null) {
				if (room.getNumber() == forRoom.getNumber()) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		// RoomService service = new FileOperator().getRoomService(dbPath +
		// getFileName(), getClientRepository(),
		// getRoomRepository(), getServiceRepository(), getOrderRepository());
		//
		// getRepository().setRepository(((RoomRepository)
		// service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) {
//		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

	public String[] getStringToSave() {
		return getRoomRepository().toStringArray();
	}
}

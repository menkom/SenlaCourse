package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.RoomRepository;
import com.senla.util.Serialization;

public class RoomService implements IService {

	private static RoomService roomService;

	private RoomRepository roomRepository;

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

	private boolean isRoomInArray(Room room, List<Room> rooms) {
		boolean result = false;
		for (Room forRoom : rooms) {
			if (forRoom != null) {
				if (room.getNumber().equals(forRoom.getNumber())) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public Boolean add(Room room) {
		return getRoomRepository().add(room);
	}

	public Boolean addRoom(Integer number, Integer capacity, RoomStar star, RoomStatus status, Integer price) {
		Room room = new Room(number, capacity, star, status, price);
		return add(room);
	}

	public List<Room> getAllRooms(Comparator<Room> comparator) {
		List<Room> result = getRoomRepository().getRooms();
		result.sort(comparator);
		return result;
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

	public List<Room> getFreeRooms(Comparator<Room> comparator) {
		List<Room> result = new ArrayList<>();

		for (Room room : getRoomRepository().getRooms()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result.add(room);
			}
		}
		result.sort(comparator);
		return result;
	}

	public Room getRoomByNum(Integer number) {
		return getRoomRepository().getRoomByNum(number);
	}

	public Boolean changeRoomStatus(Integer number, RoomStatus roomStatus) {
		Boolean result = false;
		Room room = getRoomByNum(number);
		if (room != null) {
			room.setStatus(roomStatus);
			result = true;
		}
		return result;
	}

	public Boolean changeRoomPrice(Integer number, Integer price) {
		Boolean result = false;
		Room room = getRoomByNum(number);
		if (room != null) {
			room.setPrice(price);
			result = true;
		}
		return result;
	}

	public List<Room> getFreeRooms(Date date, Comparator<Room> comparator) {

		List<Room> result = new ArrayList<>();

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
		result.sort(comparator);
		return result;
	}

	public Boolean loadFromRaw(String filePath)
			throws IOException, NumberFormatException, ParseException, ClassNotFoundException {
		Boolean result = false;
		RoomRepository rooms = Serialization.deserialize(filePath + "room.raw");

		if (rooms != null) {
			roomRepository.setLastId(rooms.getLastId());
			result = roomRepository.getRooms().addAll(rooms.getRooms());
		}
		return result;
	}

	public Boolean saveToRaw(String filePath) throws IOException {
		Boolean result = false;
		result = Serialization.serialize(getRoomRepository(), filePath + "room.raw");
		return result;
	}
}

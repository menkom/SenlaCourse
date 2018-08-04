package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.RoomRepository;

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
				if (room.getNumber() == forRoom.getNumber()) {
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

	public void add(Room room) {
		getRoomRepository().add(room);
	}

	public void addRoom(Integer number, Integer capacity, RoomStar star, RoomStatus status, Integer price) {
		Room room = new Room(number, capacity, star, status, price);
		add(room);
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

	public void loadFromFile(String filePath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(filePath + "room.db").readFromFile();

		roomRepository.getRooms().addAll(ListConverter.getRooms(array));
	}

	public void saveToFile(String filePath) {
		new TextFileWorker(filePath + "room.db").writeToFile(ListConverter.getArrayFromList(roomRepository.getRooms()));
	}

}

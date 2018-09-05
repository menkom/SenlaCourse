package com.senla.hotel.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.di.DependencyInjection;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.api.IRoomRepository;
import com.senla.hotel.services.api.IRoomService;
import com.senla.util.ExportCSV;

public class RoomService implements IRoomService {

	private static IRoomService roomService;

	private IRoomRepository roomRepository;

	public RoomService() {
		super();
		this.roomRepository = DependencyInjection.getInstance().getInterfacePair(IRoomRepository.class);
	}

	public static IRoomService getInstance() {
		if (roomService == null) {
			roomService = DependencyInjection.getInstance().getInterfacePair(IRoomService.class);
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

	@Override
	public boolean add(Room room) {
		return roomRepository.add(room);
	}

	@Override
	public boolean addAll(List<Room> rooms) {
		return roomRepository.addAll(rooms);
	}

	@Override
	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		Room room = new Room(number, capacity, star, status, price);
		return add(room);
	}

	@Override
	public boolean update(Room room) {
		return roomRepository.update(room);
	}

	@Override
	public List<Room> getRooms() {
		return roomRepository.getRooms();
	}

	@Override
	public List<Room> getAllRooms(Comparator<Room> comparator) {
		List<Room> result = getRooms();
		result.sort(comparator);
		return result;
	}

	@Override
	public int getNumberOfFreeRooms() {
		int result = 0;
		for (Room room : roomRepository.getRooms()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result++;
			}
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(Comparator<Room> comparator) {
		List<Room> result = new ArrayList<>();

		for (Room room : roomRepository.getRooms()) {
			if (room != null && room.getStatus() == RoomStatus.AVAILABLE) {
				result.add(room);
			}
		}
		result.sort(comparator);
		return result;
	}

	@Override
	public Room getRoomByNum(int number) {
		return roomRepository.getRoomByNum(number);
	}

	@Override
	public Room getRoomById(int id) {
		return roomRepository.getRoomById(id);
	}

	@Override
	public boolean changeRoomStatus(int number, RoomStatus roomStatus) {
		boolean result = false;
		Room room = getRoomByNum(number);
		if (room != null) {
			room.setStatus(roomStatus);
			result = true;
		}
		return result;
	}

	@Override
	public boolean changeRoomPrice(int number, int price) {
		boolean result = false;
		Room room = getRoomByNum(number);
		if (room != null) {
			room.setPrice(price);
			result = true;
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(Date date, Comparator<Room> comparator) {

		List<Room> result = new ArrayList<>();

		List<Room> resultExclude = new ArrayList<>();

		for (Order order : OrderService.getInstance().getOrders()) {
			if (order != null) {
				if ((date.after(order.getStartDate())
						&& ((date.before(order.getFinishDate()) || order.getFinishDate() == null)))) {
					resultExclude.add(order.getRoom());
				}
			}
		}

		for (Room room : roomRepository.getRooms()) {
			if (room != null) {
				if (!isRoomInArray(room, resultExclude)) {
					result.add(room);
				}
			}
		}
		result.sort(comparator);
		return result;
	}

	@Override
	public boolean exportRoomCSV(int roomNum, String fileName) throws IOException {
		Room room = getRoomByNum(roomNum);
		if (room == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(room.toString(), fileName);
		}
	}

	@Override
	public boolean importRoomsCSV(String file) throws IOException {
		boolean result = false;
		List<Room> rooms = ExportCSV.getRoomsFromCSV(file);
		for (Room room : rooms) {
			if (getRoomById(room.getId()) != null) {
				result = update(room);
			} else {
				result = add(room);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		return roomRepository.exportCsv(csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		return roomRepository.importCsv(csvFilePath);
	}

}

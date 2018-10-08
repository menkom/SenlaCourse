package com.senla.hotel.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.services.api.IRoomService;
import com.senla.util.ExportCSV;

public class RoomService implements IRoomService {

	private static IRoomService roomService;
	private DbConnector dbConnector;
	private IRoomDao<Room> roomDao;

	@SuppressWarnings("unchecked")
	public RoomService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.roomDao = DependencyInjection.getInstance().getInterfacePair(IRoomDao.class);
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
	public boolean add(Room room) throws SQLException {
		return roomDao.add(dbConnector.getConnection(), room);
	}

	@Override
	public boolean addAll(List<Room> rooms) throws SQLException {
		return roomDao.addAll(dbConnector.getConnection(), rooms);
	}

	@Override
	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) throws SQLException {
		Room room = new Room(number, capacity, star, status, price);
		return add(room);
	}

	@Override
	public boolean update(Room room) throws SQLException {
		return roomDao.update(dbConnector.getConnection(), room);
	}

	@Override
	public List<Room> getRooms() throws SQLException {
		return roomDao.getAll(dbConnector.getConnection(), "");
	}

	@Override
	public List<Room> getAllRooms(EnumRoomSort roomSort) throws SQLException {
		return roomDao.getAll(dbConnector.getConnection(), roomSort.getTableField());
	}

	@Override
	public int getNumberOfFreeRooms() throws SQLException {
		int result = 0;
		try (PreparedStatement ps = dbConnector.getConnection()
				.prepareStatement("SELECT count(room_id) count FROM `room` where room_roomstatus=?")) {
			ps.setString(1, RoomStatus.AVAILABLE.toString());
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(EnumRoomSort roomSort) throws SQLException {
		List<Room> result = new ArrayList<>();

		try (PreparedStatement ps = dbConnector.getConnection()
				.prepareStatement("SELECT * FROM `room` where room_roomstatus=? order by (?)")) {
			ps.setString(1, RoomStatus.AVAILABLE.toString());
			ps.setString(2, roomSort.getTableField());
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Room room = roomDao.parseResultSet(resultSet);
				result.add(room);
			}
		}
		return result;
	}

	@Override
	public Room getRoomById(int id) throws SQLException {
		return roomDao.getById(dbConnector.getConnection(), id);
	}

	@Override
	public boolean changeRoomStatus(int roomId, RoomStatus roomStatus) throws SQLException {
		boolean result = false;
		Room room = getRoomById(roomId);
		if (room != null) {
			room.setStatus(roomStatus);
			result = roomDao.update(dbConnector.getConnection(), room);
		}
		return result;
	}

	@Override
	public boolean changeRoomPrice(int roomId, int price) throws SQLException {
		boolean result = false;
		Room room = getRoomById(roomId);
		if (room != null) {
			room.setPrice(price);
			result = roomDao.update(dbConnector.getConnection(), room);
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(Date date, EnumRoomSort roomSort) throws SQLException {

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

		for (Room room : roomDao.getAll(dbConnector.getConnection(), "")) {
			if (room != null) {
				if (!isRoomInArray(room, resultExclude)) {
					result.add(room);
				}
			}
		}
		return result;
	}

	@Override
	public boolean exportRoomCSV(int roomId, String fileName) throws IOException, SQLException {
		Room room = getRoomById(roomId);
		if (room == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(room.toString(), fileName);
		}
	}

	@Override
	public boolean importRoomsCSV(String file) throws IOException, SQLException {
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
	public boolean exportCsv(String csvFilePath) throws IOException, SQLException {
		return roomDao.exportCsv(dbConnector.getConnection(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException, SQLException {
		return roomDao.importCsv(dbConnector.getConnection(), csvFilePath);
	}

}

package com.senla.hotel.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;
import com.senla.hotel.services.api.IRoomService;
import com.senla.util.ExportCSV;

public class RoomService implements IRoomService {

	private static final String SELECT_FREEROOMS = "SELECT * FROM `room` where room_roomstatus=? order by (?)";
	private static final String SELECT_COUNT_ROOM = "SELECT count(room_id) count FROM `room` where room_roomstatus=?";
	private static final String SELECT_FREE_ROOMS = "select * from room where room.room_id not in "
			+ "(SELECT order_room_id  FROM room join `order` on `order`.order_room_id=room.room_id "
			+ "where `order`.order_start_date<=? AND "
			+ "(`order`.order_finish_date>=? or `order`.order_finish_date is null)) order by (?)";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final String TABLE_COLUMN_COUNT = "count";
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
				.prepareStatement(SELECT_COUNT_ROOM)) {
			ps.setString(1, RoomStatus.AVAILABLE.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(TABLE_COLUMN_COUNT);
			}
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(EnumRoomSort roomSort) throws SQLException {
		List<Room> result = new ArrayList<>();

		try (PreparedStatement ps = dbConnector.getConnection()
				.prepareStatement(SELECT_FREEROOMS)) {
			ps.setString(1, RoomStatus.AVAILABLE.toString());
			ps.setString(2, roomSort.getTableField());
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
		try (PreparedStatement ps = dbConnector.getConnection().prepareStatement(SELECT_FREE_ROOMS)) {
			String dateStr = null;
			if (date != null) {
				dateStr = formatter.format(date);
			}

			ps.setString(1, dateStr);
			ps.setString(2, dateStr);
			ps.setString(3, roomSort.getTableField());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Room room = roomDao.parseResultSet(resultSet);
				result.add(room);
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

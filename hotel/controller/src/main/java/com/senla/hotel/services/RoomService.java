package com.senla.hotel.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

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

	private static final Logger logger = Logger.getLogger(RoomService.class);

	private static IRoomService roomService;
	private DbConnector dbConnector;
	private IRoomDao<Room> roomDao;

	@SuppressWarnings("unchecked")
	private RoomService() throws ClassNotFoundException {
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
		boolean result = false;
		Connection connection = dbConnector.getConnection();
		connection.setAutoCommit(false);
		try {
			result = roomDao.addAll(connection, rooms);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e);
			connection.rollback();
			connection.setAutoCommit(true);
			throw e;
		}
		return result;
	}

	@Override
	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) throws SQLException {
		Room room = new Room(number, capacity, star, status, price);
		return roomDao.add(dbConnector.getConnection(), room);
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
		return roomDao.getNumberOfFreeRooms(dbConnector.getConnection());
	}

	@Override
	public List<Room> getFreeRooms(EnumRoomSort roomSort) throws SQLException {
		return roomDao.getFreeRooms(dbConnector.getConnection(), roomSort);
	}

	@Override
	public Room getRoomById(int id) throws SQLException {
		return roomDao.getById(dbConnector.getConnection(), id);
	}

	@Override
	public boolean changeRoomStatus(int roomId, RoomStatus roomStatus) throws SQLException {
		boolean result = false;
		Room room = roomDao.getById(dbConnector.getConnection(), roomId);
		if (room != null) {
			room.setStatus(roomStatus);
			result = roomDao.update(dbConnector.getConnection(), room);
		}
		return result;
	}

	@Override
	public boolean changeRoomPrice(int roomId, int price) throws SQLException {
		boolean result = false;
		Room room = roomDao.getById(dbConnector.getConnection(), roomId);
		if (room != null) {
			room.setPrice(price);
			result = roomDao.update(dbConnector.getConnection(), room);
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(Date date, EnumRoomSort roomSort) throws SQLException {
		return roomDao.getFreeRooms(dbConnector.getConnection(), date, roomSort);
	}

	@Override
	public boolean exportRoomCSV(int roomId, String fileName) throws IOException, SQLException {
		Room room = roomDao.getById(dbConnector.getConnection(), roomId);
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
			if (roomDao.getById(dbConnector.getConnection(), room.getId()) != null) {
				result = roomDao.update(dbConnector.getConnection(), room);
			} else {
				result = roomDao.add(dbConnector.getConnection(), room);
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

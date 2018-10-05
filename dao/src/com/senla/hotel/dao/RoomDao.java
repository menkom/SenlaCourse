package com.senla.hotel.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.annotation.parser.CsvParser;
import com.senla.dao.DaoHandler;
import com.senla.di.DependencyInjection;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.api.IRoomRepository;

public class RoomDao implements IRoomRepository {

	private static final Logger logger = Logger.getLogger(RoomDao.class);

	private static IRoomRepository roomRepository;

	private RoomDao() {
		super();
	}

	public static IRoomRepository getInstance() {
		if (roomRepository == null) {
			roomRepository = DependencyInjection.getInstance().getInterfacePair(IRoomRepository.class);
		}
		return roomRepository;
	}

	@Override
	public List<Room> getRooms() {
		List<Room> resultList = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from room");

			while (resultSet.next()) {
				Room room = parseResultSet(resultSet);
				resultList.add(room);
			}

		} catch (SQLException e) {
			logger.error(e);
		}
		return resultList;
	}

	@Override
	public boolean add(Room room) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate(
					"insert into room (room_id, room_number, room_capacity, room_roomstar, room_roomstatus, room_price) values ("
							+ room.getId() + "," + room.getNumber() + "," + room.getCapacity() + ",\'" + room.getStar()
							+ "\',\'" + room.getStatus() + "\'," + room.getPrice() + ")");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean addAll(List<Room> rooms) {
		int result = 0;
		if (rooms.size() > 0) {
			StringBuilder query = new StringBuilder(
					"insert into room (room_id, room_number, room_capacity, room_roomstar, room_roomstatus, room_price) values ");
			for (Room room : rooms) {
				query.append("(" + room.getId() + "," + room.getNumber() + "," + room.getCapacity() + ",\'"
						+ room.getStar() + "\',\'" + room.getStatus() + "\'," + room.getPrice() + "),");
			}
			query.deleteCharAt(query.length() - 1);
			try {
				result = DaoHandler.getInstance().executeUpdate(query.toString());
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return result > 0;
	}

	@Override
	public boolean delete(Integer roomNum) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from room where room_number=\'" + roomNum + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from room where room_id=\'" + id + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public Room getRoomByNum(Integer number) {
		Room room = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance()
					.executeQuery("select * from room where room_number=\'" + number + "\'");

			while (resultSet.next()) {
				room = parseResultSet(resultSet);
				return room;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return room;
	}

	@Override
	public Room getRoomById(Integer id) {
		Room room = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from room where room_id=\'" + id + "\'");

			while (resultSet.next()) {
				room = parseResultSet(resultSet);
				return room;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return room;
	}

	@Override
	public boolean update(Room room) {
		int result = 0;
		try {
			result = DaoHandler.getInstance()
					.executeUpdate("update room set room_number=" + room.getNumber() + ", room_capacity="
							+ room.getCapacity() + ", room_roomstar=\'" + room.getStar() + "\', room_roomstatus=\'"
							+ room.getStatus() + "\', room_price=" + room.getPrice() + " where room_id=\'"
							+ room.getId() + "\'");

		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		return CsvParser.exportToCsv(getRooms(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		return addAll(CsvParser.importFromCsv(Room.class, csvFilePath));
	}

	public Room parseResultSet(ResultSet resultSet) throws SQLException {
		Room room = new Room();
		room.setId(resultSet.getInt("room_id"));
		room.setNumber(resultSet.getInt("room_number"));
		room.setCapacity(resultSet.getInt("room_capacity"));
		room.setStar(RoomStar.valueOf(resultSet.getString("room_roomstar")));
		room.setStatus(RoomStatus.valueOf(resultSet.getString("room_roomstatus")));
		room.setPrice(resultSet.getInt("room_price"));

		return room;
	}

}

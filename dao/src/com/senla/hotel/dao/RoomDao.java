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
				Room room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setNumber(resultSet.getInt("number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setStar(RoomStar.valueOf(resultSet.getString("roomstar")));
				room.setStatus(RoomStatus.valueOf(resultSet.getString("roomstatus")));
				room.setPrice(resultSet.getInt("price"));

				System.out.println("room : " + room);
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
			result = DaoHandler.getInstance()
					.executeUpdate("insert into room (id, number, capacity, roomstar, roomstatus, price) values ("
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
					"insert into room (id, number, capacity, roomstar, roomstatus, price) values ");
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
		System.out.println("addAll result:" + result);
		return result > 0;
	}

	@Override
	public boolean delete(Integer roomNum) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from room where number=\'" + roomNum + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from room where id=\'" + id + "\'");
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
			resultSet = DaoHandler.getInstance().executeQuery("select * from room where number=\'" + number + "\'");

			while (resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setNumber(resultSet.getInt("number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setStar(RoomStar.valueOf(resultSet.getString("roomstar")));
				room.setStatus(RoomStatus.valueOf(resultSet.getString("roomstatus")));
				room.setPrice(resultSet.getInt("price"));
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
			resultSet = DaoHandler.getInstance().executeQuery("select * from room where id=\'" + id + "\'");

			while (resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setNumber(resultSet.getInt("number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setStar(RoomStar.valueOf(resultSet.getString("roomstar")));
				room.setStatus(RoomStatus.valueOf(resultSet.getString("roomstatus")));
				room.setPrice(resultSet.getInt("price"));
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
					.executeUpdate("update room set number=" + room.getNumber() + ", capacity=" + room.getCapacity()
							+ ", roomstar=\'" + room.getStar() + "\', roomstatus=\'" + room.getStatus() + "\', price="
							+ room.getPrice() + " where id=\'" + room.getId() + "\'");

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

}

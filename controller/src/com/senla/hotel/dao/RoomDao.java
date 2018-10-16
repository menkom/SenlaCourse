package com.senla.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomDao extends GenericDao<Room> implements IRoomDao<Room> {

	private static final String TABLE_COLUMN_PRICE = "room_price";
	private static final String TABLE_COLUMN_ROOMSTATUS = "room_roomstatus";
	private static final String TABLE_COLUMN_ROOMSTAR = "room_roomstar";
	private static final String TABLE_COLUMN_CAPACITY = "room_capacity";
	private static final String INSERT_ENTITY = "insert into room (room_number, room_capacity,room_roomstar,room_roomstatus, room_price)"
			+ " values (?, ?, ?, ?, ?)";
	private static final String UPDATE_ENTITY = "update room set room_number=?, room_capacity=?, room_roomstar=?, "
			+ "room_roomstatus=?, room_price=?  where room_id=?";
	private static final String TABLE_NAME = "room";
	private static final String TABLE_COLUMN_ID = "room_id";
	private static final String TABLE_COLUMN_NUMBER = "room_number";

	private static final String SELECT_FREEROOMS = "SELECT * FROM `room` where room_roomstatus=? order by (?)";
	private static final String SELECT_COUNT_ROOM = "SELECT count(room_id) count FROM `room` where room_roomstatus=?";
	private static final String SELECT_FREE_ROOMS = "select * from room where room.room_id not in "
			+ "(SELECT order_room_id  FROM room join `order` on `order`.order_room_id=room.room_id "
			+ "where `order`.order_start_date<=? AND "
			+ "(`order`.order_finish_date>=? or `order`.order_finish_date is null)) order by (?)";
	private static final String TABLE_COLUMN_COUNT = "count";

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Room parseResultSet(ResultSet resultSet) throws SQLException {
		Room room = new Room();
		room.setId(resultSet.getInt(TABLE_COLUMN_ID));
		room.setNumber(resultSet.getInt(TABLE_COLUMN_NUMBER));
		room.setCapacity(resultSet.getInt(TABLE_COLUMN_CAPACITY));
		room.setStar(RoomStar.valueOf(resultSet.getString(TABLE_COLUMN_ROOMSTAR)));
		room.setStatus(RoomStatus.valueOf(resultSet.getString(TABLE_COLUMN_ROOMSTATUS)));
		room.setPrice(resultSet.getInt(TABLE_COLUMN_PRICE));

		return room;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected String getIdColumn() {
		return TABLE_COLUMN_ID;
	}

	@Override
	protected void prepareAddStatement(PreparedStatement ps, Room entity) throws SQLException {
		ps.setInt(1, entity.getNumber());
		ps.setInt(2, entity.getCapacity());
		ps.setString(3, entity.getStar().toString());
		ps.setString(4, entity.getStatus().toString());
		ps.setInt(5, entity.getPrice());
		ps.setInt(6, entity.getId());
	}

	@Override
	protected String getInsertQuery() {
		return INSERT_ENTITY;
	}

	@Override
	protected Class<Room> getTClass() {
		return Room.class;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_ENTITY;
	}

	@Override
	public int getNumberOfFreeRooms(Connection connection) throws SQLException {
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_COUNT_ROOM)) {
			ps.setString(1, RoomStatus.AVAILABLE.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(TABLE_COLUMN_COUNT);
			}
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(Connection connection, EnumRoomSort roomSort) throws SQLException {
		List<Room> result = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FREEROOMS)) {
			ps.setString(1, RoomStatus.AVAILABLE.toString());
			ps.setString(2, roomSort.getTableField());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Room room = parseResultSet(resultSet);
				result.add(room);
			}
		}
		return result;
	}

	@Override
	public List<Room> getFreeRooms(Connection connection, Date date, EnumRoomSort roomSort) throws SQLException {
		List<Room> result = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FREE_ROOMS)) {
			String dateStr = null;
			if (date != null) {
				dateStr = formatter.format(date);
			}

			ps.setString(1, dateStr);
			ps.setString(2, dateStr);
			ps.setString(3, roomSort.getTableField());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Room room = parseResultSet(resultSet);
				result.add(room);
			}
		}
		return result;
	}

}

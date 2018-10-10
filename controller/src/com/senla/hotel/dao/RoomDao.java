package com.senla.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomDao extends GenericDao<Room> implements IRoomDao<Room> {

	private static final String INSERT_ENTITY = "insert into room (room_number, room_capacity,room_roomstar,room_roomstatus, room_price)"
			+ " values (?, ?, ?, ?, ?)";
	private static final String UPDATE_ENTITY = "update room set room_number=?, room_capacity=?, room_roomstar=?, "
			+ "room_roomstatus=?, room_price=?  where room_id=?";

	@Override
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

	@Override
	protected String getTableName() {
		return "room";
	}

	@Override
	protected String getIdColumn() {
		return "room_id";
	}

	@Override
	protected void prepareAddStatement(PreparedStatement ps, Room entity) throws SQLException {
		ps.setInt(1, entity.getNumber());
		ps.setInt(2, entity.getCapacity());
		ps.setString(3, entity.getStar().toString());
		ps.setString(4, entity.getStatus().toString());
		ps.setInt(5, entity.getPrice());
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
}

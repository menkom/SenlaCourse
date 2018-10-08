package com.senla.dao.test;

import java.sql.SQLException;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.hotel.dao.RoomDao;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomTest {

	public static void testRoomDao() throws ClassNotFoundException {
		RoomDao dao = new RoomDao();
		// getAll
		try {
			List<Room> rooms = dao.getAll(DbConnector.getInstance().getConnection(), "");
			for (Room room : rooms) {
				System.out.println(room);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		// getById
		try {
			System.out.println("room[2] : " + dao.getById(DbConnector.getInstance().getConnection(), 3));
		} catch (SQLException e) {
			System.out.println(e);
		}

		// add
		try {
			System.out.println("entity add : " + dao.add(DbConnector.getInstance().getConnection(),
					new Room(123, 3, RoomStar.THREE, RoomStatus.AVAILABLE, 123)));
		} catch (SQLException e) {
			System.out.println(e);
		}

		// deleteById
//		try {
//			System.out.println("delete entity.id=10 : " + dao.delete(DbConnector.getInstance().getConnection(), 10));
//		} catch (SQLException e) {
//			System.out.println(e);
//		}

		// update
		try {
			Room room = dao.getById(DbConnector.getInstance().getConnection(), 1);
			room.setNumber(room.getNumber() + 1);
			System.out.println("room.id=1 update: " + dao.update(DbConnector.getInstance().getConnection(), room));
		} catch (SQLException e) {
			System.out.println(e);
		}

		/*
		 * IRoomRepository rooms = (RoomDao) RoomDao.getInstance();
		 * System.out.println("rooms : " + rooms.getRooms().size());
		 * 
		 * Room room = new Room(123, 3, RoomStar.THREE, RoomStatus.AVAILABLE, 123); //
		 * System.out.println("Room add:" + rooms.add(room));
		 * 
		 * List<Room> roomsToAdd = new ArrayList<>(); roomsToAdd.add(new Room(101, 1,
		 * RoomStar.ONE, RoomStatus.AVAILABLE, 101)); roomsToAdd.add(new Room(102, 2,
		 * RoomStar.TWO, RoomStatus.AVAILABLE, 102)); roomsToAdd.add(new Room(103, 3,
		 * RoomStar.THREE, RoomStatus.AVAILABLE, 103)); roomsToAdd.add(new Room(104, 4,
		 * RoomStar.FOUR, RoomStatus.AVAILABLE, 104)); //
		 * System.out.println("Rooms add all:" + rooms.addAll(roomsToAdd));
		 * 
		 * // System.out.println("room[2] : " + rooms.getRoomById(3)); // for (int i =
		 * 9; i < 19; i++) { // rooms.deleteById(i); // }
		 * 
		 * room = rooms.getRooms().get(0);
		 * 
		 * room.setNumber(room.getNumber() + 1); // rooms.update(room);
		 * System.out.println("room[0] : " + room.getNumber());
		 * DaoHandler.getInstance().closeConnection();
		 */}

}

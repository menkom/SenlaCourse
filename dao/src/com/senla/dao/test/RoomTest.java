package com.senla.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.senla.dao.DaoHandler;
import com.senla.hotel.dao.RoomDao;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.api.IRoomRepository;

public class RoomTest {

	public static void testRoomDao() {
		IRoomRepository rooms = (RoomDao) RoomDao.getInstance();
		System.out.println("rooms : " + rooms.getRooms().size());

		Room room = new Room(123, 3, RoomStar.THREE, RoomStatus.AVAILABLE, 123);
//		System.out.println("Room add:" + rooms.add(room));

		List<Room> roomsToAdd = new ArrayList<>();
		roomsToAdd.add(new Room(101, 1, RoomStar.ONE, RoomStatus.AVAILABLE, 101));
		roomsToAdd.add(new Room(102, 2, RoomStar.TWO, RoomStatus.AVAILABLE, 102));
		roomsToAdd.add(new Room(103, 3, RoomStar.THREE, RoomStatus.AVAILABLE, 103));
		roomsToAdd.add(new Room(104, 4, RoomStar.FOUR, RoomStatus.AVAILABLE, 104));
//		System.out.println("Rooms add all:" + rooms.addAll(roomsToAdd));

//		System.out.println("room[2] : " + rooms.getRoomById(3));
//		for (int i = 9; i < 19; i++) {
//			rooms.deleteById(i);
//		}

		room = rooms.getRooms().get(0);

		room.setNumber(room.getNumber() + 1);
//		rooms.update(room);
		System.out.println("room[0] : " + room.getNumber());
		DaoHandler.getInstance().closeConnection();
	}

}

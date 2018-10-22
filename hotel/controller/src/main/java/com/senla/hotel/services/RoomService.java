package com.senla.hotel.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;
import com.senla.hotel.services.api.IRoomService;
import com.senla.util.ExportCSV;

public class RoomService implements IRoomService {

	private final static Logger logger = Logger.getLogger(RoomService.class);

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
	public void add(Room room) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			roomDao.add(session, room);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void addAll(List<Room> rooms) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			roomDao.addAll(session, rooms);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void update(Room room) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			roomDao.update(session, room);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public List<Room> getRooms(EnumRoomSort roomSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			List<Room> rooms = roomDao.getAll(session, roomSort.getTableField());
			session.getTransaction().commit();
			return rooms;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public Long getNumberOfFreeRooms() {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			Long result = roomDao.getNumberOfFreeRooms(session);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {

			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public List<Room> getFreeRooms(EnumRoomSort roomSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			List<Room> rooms = roomDao.getFreeRooms(session, roomSort);
			session.getTransaction().commit();
			return rooms;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public Room getRoomById(int id) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			Room result = roomDao.getById(session, id);
			session.getTransaction().commit();
			return result;
		} catch (Exception e) {

			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void changeRoomStatus(int roomId, RoomStatus roomStatus) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Room room = roomDao.getById(session, roomId);
			if (room != null) {
				room.setStatus(roomStatus);
				roomDao.update(session, room);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void changeRoomPrice(int roomId, int price) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Room room = roomDao.getById(session, roomId);
			if (room != null) {
				room.setPrice(price);
				roomDao.update(session, room);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public List<Room> getFreeRooms(Date date, EnumRoomSort roomSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			List<Room> rooms = roomDao.getFreeRooms(session, date, roomSort);
			session.getTransaction().commit();
			return rooms;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean exportRoomCSV(int roomId, String fileName) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Room room = roomDao.getById(session, roomId);
			if (room != null) {
				result = ExportCSV.saveCSV(room.toString(), fileName);
			}

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean importRoomsCSV(String file) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Room> rooms = ExportCSV.getRoomsFromCSV(file);
			for (Room room : rooms) {
				if (roomDao.getById(session, room.getId()) != null) {
					roomDao.update(session, room);
				} else {
					roomDao.add(session, room);
				}
			}

			session.getTransaction().commit();
			result = true;
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			result = roomDao.exportCsv(session, csvFilePath);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			result = roomDao.importCsv(session, csvFilePath);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}

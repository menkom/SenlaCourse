package com.senla.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class OrderDao extends GenericDao<Order> implements IOrderDao<Order> {

	private static final String SELECT_ALL = "SELECT * FROM `%s` join client join room on `order`.order_client_id=client.client_id and `order`.order_room_id=room.room_id order by (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM `%s` join client join room on `order`.order_client_id=client.client_id and `order`.order_room_id=room.room_id where %s =?";
	private static final String SELECT_ORDER_SERVICES = "SELECT s.* FROM service s, service_order so WHERE so.so_service_id=s.service_id and so.so_order_id=?";

	private static final String INSERT_ENTITY = "insert into `order` "
			+ "(order_num, order_client_id, order_room_id, order_start_date, order_finish_date)"
			+ " values (?, ?, ?, ?, ?)";
	private static final String UPDATE_ENTITY = "update `order` set "
			+ "order_num=?, order_client_id=?, order_room_id=?, order_start_date=?, order_finish_date=?"
			+ " where order_id=?";

	private static final String ERROR_DATE_FORMAT = "Date format error.";
	private static final Logger logger = Logger.getLogger(OrderDao.class);
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected String getAllQuery() {
		return SELECT_ALL;
	}

	@Override
	protected String getByIdQuery() {
		return SELECT_BY_ID;
	}

	@Override
	public Order parseResultSet(ResultSet resultSet) throws SQLException {
		Order order = new Order();

		order.setId(resultSet.getInt("order_id"));
		order.setNum(resultSet.getInt("order_num"));

		if (isExist(resultSet, "client_id")) {
			@SuppressWarnings("unchecked")
			IClientDao<Client> clientDao = DependencyInjection.getInstance().getInterfacePair(IClientDao.class);
			Client client = clientDao.parseResultSet(resultSet);
			order.setClient(client);
		}

		if (isExist(resultSet, "room_id")) {
			@SuppressWarnings("unchecked")
			IRoomDao<Room> roomDao = DependencyInjection.getInstance().getInterfacePair(IRoomDao.class);
			Room room = roomDao.parseResultSet(resultSet);
			order.setRoom(room);
		}

		order.setStartDate(resultSet.getDate("order_start_date"));

		String dateStr = resultSet.getString("order_finish_date");

		if ((dateStr != null) && !dateStr.equals("0000-00-00")) {
			try {
				order.setFinishDate(formatter.parse(dateStr));
			} catch (ParseException e) {
				logger.error(ERROR_DATE_FORMAT, e);
			}
		}
		return order;
	}

	@Override
	protected String getTableName() {
		return "order";
	}

	@Override
	protected String getIdColumn() {
		return "order_id";
	}

	@Override
	public int add(Connection connection, Order entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_ENTITY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, entity.getNum());
			ps.setInt(2, entity.getClient().getId());
			ps.setInt(3, entity.getRoom().getId());

			String dateStart = null;
			if (entity.getStartDate() != null) {
				dateStart = formatter.format(entity.getStartDate());
			}
			ps.setString(4, dateStart);

			String dateFinish = null;
			if (entity.getFinishDate() != null) {
				dateFinish = formatter.format(entity.getFinishDate());
			}
			ps.setString(5, dateFinish);

			System.out.println(ps);

			int result = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int lastInsertedId = rs.getInt(1);
				entity.setId(lastInsertedId);
			}

			return result;
		}
	}

	@Override
	public int update(Connection connection, Order entity) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_ENTITY)) {
			System.out.println("update");

			ps.setInt(1, entity.getNum());
			ps.setInt(2, entity.getClient().getId());
			ps.setInt(3, entity.getRoom().getId());

			String dateStart = null;
			if (entity.getStartDate() != null) {
				dateStart = formatter.format(entity.getStartDate());
			}
			ps.setString(4, dateStart);

			String dateFinish = null;
			if (entity.getFinishDate() != null) {
				dateFinish = formatter.format(entity.getFinishDate());
			}
			ps.setString(5, dateFinish);

			ps.setInt(6, entity.getId());
			System.out.println(ps);

			return ps.executeUpdate();
		}
	}

	@Override
	public List<Service> getServices(Connection connection, int orderId) throws SQLException {
		List<Service> result = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_SERVICES)) {
			ps.setInt(1, orderId);
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ServiceDao serviceDao = new ServiceDao();
				Service entity = serviceDao.parseResultSet(resultSet);
				result.add(entity);
			}
		}
		return result;
	}

}

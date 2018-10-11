package com.senla.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private static final String TABLE_COLUMN_SERVICE_ID = "service_id";
	private static final String ZERO_DATE = "0000-00-00";
	private static final String INSERT_SERVICE_TO_ORDER = "insert into `service_order` (so_service_id, so_order_id) values ( ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM `%s` join client join room on `order`.order_client_id=client.client_id and `order`.order_room_id=room.room_id order by (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM `%s` join client join room on `order`.order_client_id=client.client_id and `order`.order_room_id=room.room_id where %s =?";
	private static final String SELECT_ORDER_SERVICES = "SELECT s.* FROM service s, service_order so WHERE so.so_service_id=s.service_id and so.so_order_id=? order by (?)";

	private static final String INSERT_ENTITY = "insert into `order` "
			+ "(order_num, order_client_id, order_room_id, order_start_date, order_finish_date)"
			+ " values (?, ?, ?, ?, ?)";
	private static final String UPDATE_ENTITY = "update `order` set "
			+ "order_num=?, order_client_id=?, order_room_id=?, order_start_date=?, order_finish_date=?"
			+ " where order_id=?";

	private static final String TABLE_NAME = "order";
	private static final String TABLE_COLUMN_ID = "order_id";
	private static final String TABLE_COLUMN_NUM = "order_num";
	private static final String TABLE_COLUMN_JOIN_CLIENT_ID = "client_id";
	private static final String TABLE_COLUMN_JOIN_ROOM_ID = "room_id";
	private static final String TABLE_COLUMN_DATE_START = "order_start_date";
	private static final String TABLE_COLUMN_DATE_FINISH = "order_finish_date";

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

		order.setId(resultSet.getInt(TABLE_COLUMN_ID));
		order.setNum(resultSet.getInt(TABLE_COLUMN_NUM));

		if (isExist(resultSet, TABLE_COLUMN_JOIN_CLIENT_ID)) {
			@SuppressWarnings("unchecked")
			IClientDao<Client> clientDao = DependencyInjection.getInstance().getInterfacePair(IClientDao.class);
			Client client = clientDao.parseResultSet(resultSet);
			order.setClient(client);
		}

		if (isExist(resultSet, TABLE_COLUMN_JOIN_ROOM_ID)) {
			@SuppressWarnings("unchecked")
			IRoomDao<Room> roomDao = DependencyInjection.getInstance().getInterfacePair(IRoomDao.class);
			Room room = roomDao.parseResultSet(resultSet);
			order.setRoom(room);
		}

		order.setStartDate(resultSet.getDate(TABLE_COLUMN_DATE_START));

		String dateStr = resultSet.getString(TABLE_COLUMN_DATE_FINISH);

		if ((dateStr != null) && !dateStr.equals(ZERO_DATE)) {
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
		return TABLE_NAME;
	}

	@Override
	protected String getIdColumn() {
		return TABLE_COLUMN_ID;
	}

	@Override
	public List<Service> getServices(Connection connection, int orderId, String sortColumn) throws SQLException {
		List<Service> result = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_SERVICES)) {
			ps.setInt(1, orderId);
			ps.setString(2, (sortColumn.equals("") ? TABLE_COLUMN_SERVICE_ID : sortColumn));
			logger.info(ps);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ServiceDao serviceDao = new ServiceDao();
				Service entity = serviceDao.parseResultSet(resultSet);
				result.add(entity);
			}
		}
		return result;
	}

	@Override
	protected void prepareAddStatement(PreparedStatement ps, Order entity) throws SQLException {
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
	}

	@Override
	protected String getInsertQuery() {
		return INSERT_ENTITY;
	}

	@Override
	protected Class<Order> getTClass() {
		return Order.class;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_ENTITY;
	}

	@Override
	public boolean addOrderService(Connection connection, int orderId, Service service) throws SQLException {
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(INSERT_SERVICE_TO_ORDER)) {
			ps.setInt(1, orderId);
			ps.setInt(2, service.getId());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}
}

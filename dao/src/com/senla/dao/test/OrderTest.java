package com.senla.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.senla.dao.DaoHandler;
import com.senla.hotel.dao.ClientDao;
import com.senla.hotel.dao.OrderDao;
import com.senla.hotel.dao.RoomDao;
import com.senla.hotel.dao.ServiceDao;
import com.senla.hotel.model.Order;
import com.senla.hotel.repository.api.IOrderRepository;

public class OrderTest {

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public static void testOrderDao() {
		IOrderRepository dao = OrderDao.getInstance();
		// getAll
		System.out.println("orders : " + dao.getOrders().size());

		for (Order order : dao.getOrders()) {
			System.out.println(order);
		}

		// insert
//		Order order = new Order();
//		order.setNum(701);
//		order.setClient(ClientDao.getInstance().getClientById(3));
//		order.setRoom(RoomDao.getInstance().getRoomById(3));
//		try {
//			order.setStartDate(formatter.parse("2018-10-04"));
//		} catch (ParseException e) {
//			System.out.println(e);
//		}
//		System.out.println("Order add:" + dao.add(order));

		Order order;
		try {
			order = dao.getOrders().get(0).clone();
			order.setNum(701);
			order.addService(((ServiceDao) ServiceDao.getInstance()).getServiceById(1));
			order.addService(((ServiceDao) ServiceDao.getInstance()).getServiceById(2));
			System.out.println("Order add:" + dao.add(order));
		} catch (CloneNotSupportedException e) {
			System.out.println(e);
		}

		// addAll
//		List<Order> ordersToAdd = new ArrayList<>();
//		try {
//			ordersToAdd.add(new Order(701, ClientDao.getInstance().getClientById(3),
//					RoomDao.getInstance().getRoomById(3), formatter.parse("2018-10-03"), null));
//			ordersToAdd.add(new Order(701, ClientDao.getInstance().getClientById(4),
//					RoomDao.getInstance().getRoomById(4), formatter.parse("2018-10-04"), null));
//			ordersToAdd.add(new Order(701, ClientDao.getInstance().getClientById(5),
//					RoomDao.getInstance().getRoomById(5), formatter.parse("2018-10-05"), null));
//		} catch (ParseException e) {
//			System.out.println(e);
//		}
//		System.out.println("Orders add all:" + dao.addAll(ordersToAdd));

		// deleteById
//		for (int i = 12; i <= 15; i++) {
//			dao.deleteById(i);
//		}

		// delete(num)
//		dao.delete(701);

		// getById
		System.out.println("order[2] : " + dao.getOrderById(3));

//		// update
//		order = dao.getOrders().get(0);
//		order.setNum(order.getNum() + 1);
//		dao.update(order);
//		System.out.println("service[0] : " + order.getNum());

		DaoHandler.getInstance().closeConnection();
	}

}

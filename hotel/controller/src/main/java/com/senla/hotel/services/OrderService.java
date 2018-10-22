package com.senla.hotel.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.services.api.IOrderService;
import com.senla.util.ExportCSV;

public class OrderService implements IOrderService {

	private final static Logger logger = Logger.getLogger(OrderService.class);

	private static IOrderService orderService;

	private DbConnector dbConnector;

	private IOrderDao<Order> orderDao;

	@SuppressWarnings("unchecked")
	private OrderService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.orderDao = DependencyInjection.getInstance().getInterfacePair(IOrderDao.class);
	}

	public static IOrderService getInstance() {
		if (orderService == null) {
			orderService = DependencyInjection.getInstance().getInterfacePair(IOrderService.class);
		}
		return orderService;
	}

	@Override
	public void add(Order order) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			
			orderDao.add(session, order);
		
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
	public void addAll(List<Order> orders) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			orderDao.addAll(session, orders);
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
	public void update(Order order) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			orderDao.update(session, order);
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
	public List<Order> getOrders() {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Order> orders = orderDao.getAll(session, "");

			session.getTransaction().commit();
			return orders;
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
	public Order getOrderById(int id) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Order result = orderDao.getById(session, id);

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
	public void freeRoom(int orderId) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Order order = orderDao.getById(session, orderId);

			if (order != null) {
				order.setFinishDate(new Date());
				order.getRoom().setStatus(RoomStatus.AVAILABLE);
				orderDao.update(session, order);
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
	public void addOrderService(Order order, Service service) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			orderDao.addOrderService(session, order, service);

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
	public Integer getOrderPrice(Order order) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Integer result = null;
			if (order != null) {
				if (order.getFinishDate() == null) {
					result = 0;
				} else {
					Date d1 = order.getStartDate();
					Date d2 = order.getFinishDate();

					int daysBetween = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

					result = daysBetween * order.getRoom().getPrice();

					for (Service service : order.getServices()) {
						result += service.getPrice();
					}
				}
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
	public List<Order> getActiveOrders(EnumOrderSort orderSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Order> orders = orderDao.getActiveOrders(session, orderSort);

			session.getTransaction().commit();
			return orders;
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
	public List<Order> getOrdersByRoom(Room room) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Order> orders = orderDao.getOrdersByRoom(session, room);

			session.getTransaction().commit();
			return orders;
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
	public List<Order> getLastOrdersByRoom(Room room, int maxOrders, EnumOrderSort orderSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Order> orders = orderDao.getLastOrdersByRoom(session, room, maxOrders, orderSort);

			session.getTransaction().commit();
			return orders;
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
	public List<Service> getOrderServices(Order order, EnumServiceSort serviceSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Service> orders = orderDao.getServices(session, order, serviceSort.getTableField());

			session.getTransaction().commit();
			return orders;
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
	public Order cloneOrder(Order order) throws CloneNotSupportedException {
		Session session = null;
		Order result = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			if (order != null) {
				result = order.clone();
				orderDao.add(session, result);
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
	public boolean exportOrderCSV(int orderId, String fileName) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Order order = orderDao.getById(session, orderId);
			if (order != null) {
				result = ExportCSV.saveCSV(order.toString(), fileName);
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
	public boolean importOrdersCSV(String file) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Order> orders = ExportCSV.getOrdersFromCSV(file);
			for (Order order : orders) {
				if (orderDao.getById(session, order.getId()) != null) {
					orderDao.update(session, order);
				} else {
					orderDao.add(session, order);
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

			result = orderDao.exportCsv(session, csvFilePath);

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

			result = orderDao.importCsv(session, csvFilePath);

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
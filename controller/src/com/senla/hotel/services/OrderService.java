package com.senla.hotel.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.exception.NoEntryException;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.OrderRepository;
import com.senla.util.ExportCSV;

public class OrderService implements IService {

	private static OrderService orderService;

	private OrderRepository orderRepository;

	private OrderService() {
		super();
		this.orderRepository = OrderRepository.getInstance();
	}

	public static OrderService getInstance() {
		if (orderService == null) {
			orderService = new OrderService();
		}
		return orderService;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public Boolean add(Order order) {
		return orderRepository.add(order);
	}

	public Boolean addOrder(Integer num, String clientName, Integer roomNum, Date startDate, Date finishDate)
			throws NoEntryException {
		Client client = ClientService.getInstance().getClientByName(clientName);
		Room room = RoomService.getInstance().getRoomByNum(roomNum);

		Order order = new Order(num, client, room, startDate, finishDate);
		return add(order);
	}

	public Boolean update(Order order) {
		return orderRepository.update(order);
	}

	public Order getOrderByNum(Integer num) {
		return getOrderRepository().getOrderByNum(num);
	}

	public Order getOrderById(Integer id) {
		return getOrderRepository().getOrderById(id);
	}

	public Boolean orderRoom(Integer orderNum, Integer roomNum, String clientName, Date dateStart, Date dateFinish)
			throws NoEntryException {
		Boolean result = false;
		Room room = RoomService.getInstance().getRoomByNum(roomNum);
		Order order = new Order(orderNum, ClientService.getInstance().getClientByName(clientName), room, dateStart,
				dateFinish);
		result = add(order);
		if (order.getStartDate().equals(new Date())) {
			order.getRoom().setStatus(RoomStatus.OCCUPIED);
		}
		return result;
	}

	public Boolean freeRoom(Integer orderNum) {
		Boolean result = false;
		Order order = orderRepository.getOrderByNum(orderNum);

		if (order != null) {
			order.setFinishDate(new Date());
			order.getRoom().setStatus(RoomStatus.AVAILABLE);
			result = true;
		}
		return result;
	}

	public Boolean addOrderService(Integer orderNum, Integer serviceCode) {
		Service service = ServiceService.getInstance().getServiceByCode(serviceCode);
		return getOrderRepository().addOrderService(orderNum, service);
	}

	public Integer getOrderPrice(Integer orderNum) {
		Order order = getOrderByNum(orderNum);
		Integer result;
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
		return result;
	}

	public List<Order> getActiveOrders(Comparator<Order> comparator) {
		List<Order> result = new ArrayList<>();

		for (Order order : getOrderRepository().getOrders()) {
			Date currentDate = new Date();
			if (order != null) {
				if (currentDate.after(order.getStartDate())
						&& ((order.getFinishDate() == null) || (currentDate.before(order.getFinishDate())))) {
					result.add(order);
				}
			}
		}
		result.sort(comparator);
		return result;
	}

	public List<Order> getOrdersByRoom(int num) {

		List<Order> result = new ArrayList<>();

		for (Order order : getOrderRepository().getOrders()) {
			if (order != null) {
				if (order.getRoom().getNumber() == num) {
					result.add(order);
				}
			}
		}
		return result;
	}

	public List<Order> getLastOrdersByRoom(int num, int maxOrders, Comparator<Order> comparator) {

		List<Order> result = new ArrayList<>();
		List<Order> orders = getOrdersByRoom(num);
		orders.sort(new OrderSortByFinishDate());

		int j = 0;
		for (int i = orders.size() - 1; i >= 0; i--) {
			if (orders.get(i) != null) {
				result.add(orders.get(i));
				if (j == maxOrders - 1) {
					break;
				} else {
					j++;
				}
			}
		}
		result.sort(comparator);
		return result;
	}

	public List<Service> getOrderServices(int orderNum, Comparator<Service> comparator) {
		List<Service> result = null;
		Order order = getOrderByNum(orderNum);
		if ((order != null) && (order.getServices() != null)) {
			result = order.getServices();
			result.sort(comparator);
		}
		return result;
	}

	public Order cloneOrder(Integer orderNum) throws CloneNotSupportedException {
		Order orderToClone = orderRepository.getOrderByNum(orderNum);
		if (orderToClone == null) {
			return null;
		} else {
			return orderToClone.clone();
		}
	}

	public Boolean exportOrderCSV(Integer orderNum) throws NoEntryException, IOException {
		Order order = getOrderByNum(orderNum);
		if (order == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(order.toString(), "order_" + order.getId() + ".csv");
		}
	}

	public Boolean importOrdersCSV(String file) throws NoEntryException, IOException {
		Boolean result = false;
		List<Order> orders = ExportCSV.getOrdersFromCSV(file);
		for (Order order : orders) {
			if (getOrderById(order.getId()) != null) {
				update(order);
			} else {
				add(order);
			}
		}
		result = true;
		return result;
	}

}
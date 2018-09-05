package com.senla.hotel.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.di.DependencyInjection;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.api.IOrderRepository;
import com.senla.hotel.services.api.IOrderService;
import com.senla.util.ExportCSV;

public class OrderService implements IOrderService {

	private static IOrderService orderService;

	private IOrderRepository orderRepository;

	public OrderService() {
		super();
		this.orderRepository = DependencyInjection.getInstance().getInterfacePair(IOrderRepository.class);
	}

	public static IOrderService getInstance() {
		if (orderService == null) {
			orderService = DependencyInjection.getInstance().getInterfacePair(IOrderService.class);
		}
		return orderService;
	}

	@Override
	public boolean add(Order order) {
		return orderRepository.add(order);
	}

	@Override
	public boolean addAll(List<Order> orders) {
		return orderRepository.addAll(orders);
	}

	@Override
	public boolean addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {
		Client client = ClientService.getInstance().getClientByName(clientName);
		Room room = RoomService.getInstance().getRoomByNum(roomNum);

		Order order = new Order(num, client, room, startDate, finishDate);
		return add(order);
	}

	@Override
	public boolean update(Order order) {
		return orderRepository.update(order);
	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.getOrders();
	}

	@Override
	public Order getOrderByNum(int num) {
		return orderRepository.getOrderByNum(num);
	}

	@Override
	public Order getOrderById(int id) {
		return orderRepository.getOrderById(id);
	}

	@Override
	public boolean orderRoom(int orderNum, int roomNum, String clientName, Date dateStart, Date dateFinish) {
		boolean result = false;
		Room room = RoomService.getInstance().getRoomByNum(roomNum);
		Order order = new Order(orderNum, ClientService.getInstance().getClientByName(clientName), room, dateStart,
				dateFinish);
		result = add(order);
		if (order.getStartDate().equals(new Date())) {
			order.getRoom().setStatus(RoomStatus.OCCUPIED);
		}
		return result;
	}

	@Override
	public boolean freeRoom(int orderNum) {
		boolean result = false;
		Order order = orderRepository.getOrderByNum(orderNum);

		if (order != null) {
			order.setFinishDate(new Date());
			order.getRoom().setStatus(RoomStatus.AVAILABLE);
			result = true;
		}
		return result;
	}

	@Override
	public boolean addOrderService(int orderNum, int serviceCode) {
		Service service = ServiceService.getInstance().getServiceByCode(serviceCode);
		return orderRepository.addOrderService(orderNum, service);
	}

	@Override
	public Integer getOrderPrice(int orderNum) {
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

	@Override
	public List<Order> getActiveOrders(Comparator<Order> comparator) {
		List<Order> result = new ArrayList<>();

		for (Order order : orderRepository.getOrders()) {
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

	@Override
	public List<Order> getOrdersByRoom(int num) {

		List<Order> result = new ArrayList<>();

		for (Order order : orderRepository.getOrders()) {
			if (order != null) {
				if (order.getRoom().getNumber() == num) {
					result.add(order);
				}
			}
		}
		return result;
	}

	@Override
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

	@Override
	public List<Service> getOrderServices(int orderNum, Comparator<Service> comparator) {
		List<Service> result = null;
		Order order = getOrderByNum(orderNum);
		if ((order != null) && (order.getServices() != null)) {
			result = order.getServices();
			result.sort(comparator);
		}
		return result;
	}

	@Override
	public Order cloneOrder(int orderNum) throws CloneNotSupportedException {
		Order orderToClone = orderRepository.getOrderByNum(orderNum);
		if (orderToClone == null) {
			return null;
		} else {
			return orderToClone.clone();
		}
	}

	@Override
	public boolean exportOrderCSV(int orderNum, String fileName) throws IOException {
		Order order = getOrderByNum(orderNum);
		if (order == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(order.toString(), fileName);
		}
	}

	@Override
	public boolean importOrdersCSV(String file) throws IOException {
		boolean result = false;
		List<Order> orders = ExportCSV.getOrdersFromCSV(file);
		for (Order order : orders) {
			if (getOrderById(order.getId()) != null) {
				result = update(order);
			} else {
				result = add(order);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		return orderRepository.exportCsv(csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		return orderRepository.importCsv(csvFilePath);
	}

}
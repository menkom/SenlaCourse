package com.senla.hotel;

import com.senla.hotel.array.ClientArray;
import com.senla.hotel.array.OrderArray;
import com.senla.hotel.array.RoomArray;
import com.senla.hotel.array.ServiceArray;
import com.senla.hotel.services.ClientService;
import com.senla.hotel.services.OrderService;
import com.senla.hotel.services.RoomService;
import com.senla.hotel.services.ServiceService;

public abstract class AHotel {

	private ClientService clientService;
	private RoomService roomService;
	private ServiceService serviceService;
	private OrderService orderService;

	public AHotel() {
		ClientArray clientRepository = new ClientArray();
		RoomArray roomRepository = new RoomArray();
		ServiceArray serviceRepository = new ServiceArray();
		OrderArray orderRepository = new OrderArray();

		clientService = new ClientService(clientRepository, roomRepository, serviceRepository, orderRepository);
		roomService = new RoomService(clientRepository, roomRepository, serviceRepository, orderRepository);
		serviceService = new ServiceService(clientRepository, roomRepository, serviceRepository, orderRepository);
		orderService = new OrderService(clientRepository, roomRepository, serviceRepository, orderRepository);

	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}

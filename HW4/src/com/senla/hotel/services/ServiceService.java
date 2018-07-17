package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;

import com.senla.base.BaseObject;
import com.senla.hotel.repository.ClientRepository;
import com.senla.hotel.repository.IBaseRepository;
import com.senla.hotel.repository.OrderRepository;
import com.senla.hotel.repository.RoomRepository;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.hotel.model.Service;
import com.senla.util.FileOperator;

public class ServiceService implements IService {

	private ClientRepository clientRepository;
	private RoomRepository roomRepository;
	private ServiceRepository serviceRepository;
	private OrderRepository orderRepository;

	public ServiceService(ClientRepository clientRepository, RoomRepository roomRepository,
			ServiceRepository serviceRepository, OrderRepository orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;
	}

	public String getFileName() {
		return "service.db";
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public ServiceRepository getServiceRepository() {
		return serviceRepository;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void add(BaseObject element) {
		getServiceRepository().add(element);
	}

	public void addService(int code, String name, int price) {
		Service element = new Service(code, name, price);
		getRepository().add(element);
	}

	public Service[] getAllServices() {
		return (Service[]) getServiceRepository().getRepository();
	}

	public Service getServiceByCode(int code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		ServiceService service = new FileOperator().getServiceService(dbPath + getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setRepository(((ServiceRepository) service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) throws IOException {
		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

	@Override
	public IBaseRepository getRepository() {
		return getServiceRepository();
	}

	@Override
	public String[] getStringToSave() {
		return getRepository().toStringArray();
	}

	public void changeServicePrice(int code, int price) {
		Service service = getServiceByCode(code);
		if (service != null) {
			service.setPrice(price);
		}
	}

}

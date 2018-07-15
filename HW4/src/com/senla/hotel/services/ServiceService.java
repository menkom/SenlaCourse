package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;

import com.senla.base.BaseObject;
import com.senla.hotel.array.ClientArray;
import com.senla.hotel.array.IBaseArray;
import com.senla.hotel.array.OrderArray;
import com.senla.hotel.array.RoomArray;
import com.senla.hotel.array.ServiceArray;
import com.senla.hotel.model.Service;
import com.senla.util.FileOperator;

public class ServiceService implements IService {

	private ClientArray clientRepository;
	private RoomArray roomRepository;
	private ServiceArray serviceRepository;
	private OrderArray orderRepository;

	public ServiceService(ClientArray clientRepository, RoomArray roomRepository, ServiceArray serviceRepository,
			OrderArray orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;
	}

	public String getFileName() {
		return "service.db";
	}

	public ClientArray getClientRepository() {
		return clientRepository;
	}

	public RoomArray getRoomRepository() {
		return roomRepository;
	}

	public ServiceArray getServiceRepository() {
		return serviceRepository;
	}

	public OrderArray getOrderRepository() {
		return orderRepository;
	}

	public void add(BaseObject element) {
		getServiceRepository().add(element);
	}

	public void addService(int code, String name, int price) {
		Service element = new Service(code, name, price);
		getRepository().add(element);
	}

	public Service getServiceByCode(int code) {
		return getServiceRepository().getServiceByCode(code);
	}

	public void loadFromDB() throws IOException, NumberFormatException, ParseException {
		ServiceService service = new FileOperator().getServiceService(getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setArray(((ServiceArray) service.getRepository()).getArray());
	}

	public void saveToDB() throws IOException {
		new FileOperator().saveToDB(getFileName(), getStringToSave());
	}

	@Override
	public IBaseArray getRepository() {
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

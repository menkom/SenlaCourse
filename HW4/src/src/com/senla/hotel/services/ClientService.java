package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;

import com.senla.base.BaseObject;
import com.senla.hotel.array.ClientArray;
import com.senla.hotel.array.IBaseArray;
import com.senla.hotel.array.OrderArray;
import com.senla.hotel.array.RoomArray;
import com.senla.hotel.array.ServiceArray;
import com.senla.hotel.model.Client;
import com.senla.util.DispayOperator;
import com.senla.util.FileOperator;

public class ClientService implements IService {

	private ClientArray clientRepository;
	private RoomArray roomRepository;
	private ServiceArray serviceRepository;
	private OrderArray orderRepository;

	public ClientService(ClientArray clientRepository, RoomArray roomRepository, ServiceArray serviceRepository,
			OrderArray orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;
	}

	public String getFileName() {
		return "client.db";
	}

	public void add(BaseObject element) {
		new DispayOperator().printMessage("ClientService. Added element");
		new DispayOperator().printMessage("Element.class >" + element.getClass().getName());
		new DispayOperator().printMessage("Element>" + element.toString());

		new DispayOperator().printMessage("rep.class>" + getRepository().getClass());
		new DispayOperator().printMessage("rep.length before>" + ((ClientArray) getRepository()).getArray().length);
		getRepository().add(element);
		new DispayOperator().printMessage("rep.length after>" + ((ClientArray) getRepository()).getArray().length);

	}

	public Client getClientByName(String name) {
		return getClientRepository().getClientByName(name);
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

	public void loadFromDB() throws IOException, NumberFormatException, ParseException {
		// We need to add information from file or replace all with new info from
		// file?
		// Answer: As we load and save only once at the beginning and at the end we
		// expect that OUR repository is empty

		ClientService service = new FileOperator().getClientService(getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setArray(((ClientArray) service.getRepository()).getArray());
	}

	public void saveToDB() throws IOException {
		new FileOperator().saveToDB(getFileName(), getStringToSave());
	}

	@Override
	public IBaseArray getRepository() {
		return getClientRepository();
	}

	public String[] getStringToSave() {
		return getRepository().toStringArray();
	}

}

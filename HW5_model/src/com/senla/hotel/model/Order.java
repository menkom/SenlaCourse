package com.senla.hotel.model;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.senla.base.BaseObject;

public class Order extends BaseObject {

	private int num;
	private Client client;
	private Room room;
	private Date startDate;
	private Date finishDate;
	private List<Service> services;

	public Order(int num, Client client, Room room, Date startDate, Date finishDate) {
		super();
		this.num = num;
		this.client = client;
		this.room = room;
		this.startDate = startDate;
		this.finishDate = finishDate;
		services = new ArrayList<Service>();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Service> getServices() {
		return services;
	}

	// public void setServices(Service[] services) {
	// this.services = services;
	// }

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void addService(Service service) {
		services.add(service);
	}

	public String toString() {
		String result = super.toString();

		result = result.concat(getNum() + BaseObject.SEPARATOR);
		result = result.concat(getClient().getName() + BaseObject.SEPARATOR);
		result = result.concat(getRoom().getNumber() + BaseObject.SEPARATOR);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		result = result.concat(formatter.format(getStartDate()) + BaseObject.SEPARATOR);

		if (getFinishDate() == null) {
			result = result.concat("null");
		} else {
			result = result.concat(formatter.format(getFinishDate()) + BaseObject.SEPARATOR);
		}
		for (Service service : getServices()) {
			if (service != null) {
				result = result.concat(service.getCode() + BaseObject.SEPARATOR);
			}
		}

		return result;
	}

}
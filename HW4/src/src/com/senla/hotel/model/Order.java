package com.senla.hotel.model;

import java.util.Date;

import com.senla.base.BaseObject;
import com.senla.util.ArrayOperator;
import com.senla.util.DateOperator;

public class Order extends BaseObject {

	private int num;
	private Client client;
	private Room room;
	private Date startDate;
	private Date finishDate;
	private Service[] services;

	public Order(int num, Client client, Room room, Date startDate, Date finishDate) {
		super();
		this.num = num;
		this.client = client;
		this.room = room;
		this.startDate = startDate;
		this.finishDate = finishDate;
		services = new Service[0];
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

	public Service[] getServices() {
		return services;
	}

	public void setServices(Service[] services) {
		this.services = services;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void addService(Service service) {
		setServices((Service[]) new ArrayOperator().add(getServices(), service));
	}

	public String toString() {
		// TODO fill with body
		String result = "";

		result.concat(super.toString());

		result.concat(getNum() + BaseObject.SEPARATOR);
		result.concat(getClient().getName() + BaseObject.SEPARATOR);
		result.concat(getRoom().getNumber() + BaseObject.SEPARATOR);
		result.concat(new DateOperator().getDateToString(getStartDate()) + BaseObject.SEPARATOR);
		result.concat(new DateOperator().getDateToString(getFinishDate()) + BaseObject.SEPARATOR);

		for (Service service : getServices()) {
			if (service != null) {
				result.concat(service.getCode() + BaseObject.SEPARATOR);
			}
		}

		return result;
	}

}
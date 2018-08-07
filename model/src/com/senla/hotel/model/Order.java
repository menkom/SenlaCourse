package com.senla.hotel.model;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.senla.base.BaseObject;

public class Order extends BaseObject implements Cloneable {

	private Integer num;
	private Client client;
	private Room room;
	private Date startDate;
	private Date finishDate;
	private List<Service> services;

	public Order(Integer num, Client client, Room room, Date startDate, Date finishDate) {
		super();
		this.num = num;
		this.client = client;
		this.room = room;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.services = new ArrayList<Service>();
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public List<Service> getServices() {
		return services;
	}

	public Boolean addService(Service service) {
		return services.add(service);
	}

	public Order clone() throws CloneNotSupportedException {
		Order clone = (Order) super.clone();
		clone.setId(null);
		clone.setNum(null);
		clone.setClient((Client) client.clone());
		clone.setRoom((Room) room.clone());
		clone.services = new ArrayList<Service>();
		for (Service service : getServices()) {
			clone.getServices().add((Service) service.clone());
		}
		return clone;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());

		builder.append(getNum() + SEPARATOR);
		builder.append(getClient().getId() + SEPARATOR);
		builder.append(getRoom().getId() + SEPARATOR);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		if (getStartDate() == null) {
			builder.append("null" + SEPARATOR);
		} else {
			builder.append(formatter.format(getStartDate()) + SEPARATOR);
		}

		if (getFinishDate() == null) {
			builder.append("null" + SEPARATOR);
		} else {
			builder.append(formatter.format(getFinishDate()) + SEPARATOR);
		}

		for (Service service : getServices()) {
			if (service != null) {
				builder.append(service.getId() + SEPARATOR);
			}
		}

		return builder.toString();
	}

}
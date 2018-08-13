package com.senla.hotel.model;

import java.util.List;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.senla.base.BaseObject;

public class Order extends BaseObject implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566079556846351806L;
	private Integer num;
	private Client client;
	private Room room;
	private Date startDate;
	private Date finishDate;
	private List<Service> services;

	public Order() {
		super();
	}

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

		builder.append(getNum()).append(SEPARATOR);
		if (getClient() == null) {
			builder.append("null" + SEPARATOR);
		} else {
			builder.append(getClient().getId()).append(SEPARATOR);
		}
		if (getRoom() == null) {
			builder.append("null").append(SEPARATOR);
		} else {
			builder.append(getRoom().getId()).append(SEPARATOR);
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		if (getStartDate() == null) {
			builder.append("null").append(SEPARATOR);
		} else {
			builder.append(formatter.format(getStartDate())).append(SEPARATOR);
		}

		if (getFinishDate() == null) {
			builder.append("null").append(SEPARATOR);
		} else {
			builder.append(formatter.format(getFinishDate())).append(SEPARATOR);
		}

		for (Service service : getServices()) {
			if (service != null) {
				builder.append(service.getId()).append(SEPARATOR);
			}
		}

		return builder.toString();
	}

}
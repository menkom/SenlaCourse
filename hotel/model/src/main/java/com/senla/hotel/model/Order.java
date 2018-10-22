package com.senla.hotel.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;

@Entity
@Table(name = "order", schema = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = "order_id"))
@CsvEntity(filename = "order.csv")
public class Order extends BaseObject implements Cloneable {

	@Id
	@PrimaryKeyJoinColumn
	@Column(name = "order_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 0)
	private Integer id;

	@Basic
	@Column(name = "order_num", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private Integer num;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_client_id", nullable = false)
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 2, keyField = "id")
	private Client client;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_room_id", nullable = false)
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 3, keyField = "id")
	private Room room;

	@Column(name = "order_start_date", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private Date startDate;

	@Column(name = "order_finish_date", nullable = true)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	private Date finishDate;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "service_order", joinColumns = @JoinColumn(name = "so_order_id"), inverseJoinColumns = @JoinColumn(name = "so_service_id"))
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 6, keyField = "id")
	private List<Service> services;

	public Order() {
		super();
		this.services = new ArrayList<Service>();
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

	public Boolean addServices(List<Service> services) {
		return this.services.addAll(services);
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

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
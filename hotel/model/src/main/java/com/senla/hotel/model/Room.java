package com.senla.hotel.model;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import javax.persistence.*;

@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = "room_id"))
@CsvEntity(filename = "room.csv")
public class Room extends BaseObject {

	@Id
	@PrimaryKeyJoinColumn
	@Column(name = "room_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 0)
	private Integer id;

	@Basic
	@Column(name = "room_number", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private Integer number;

	@Basic
	@Column(name = "room_capacity", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private Integer capacity;

	@Enumerated(EnumType.STRING)
	@Column(name = "room_roomstar", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private RoomStar star;

	@Enumerated(EnumType.STRING)
	@Column(name = "room_roomstatus", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private RoomStatus status;

	@Basic
	@Column(name = "room_price", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	private Integer price;

	public Room() {
		super();
	}

	public Room(Integer number, Integer capacity, RoomStar star, RoomStatus status, Integer price) {
		super();
		this.number = number;
		this.capacity = capacity;
		this.star = star;
		this.status = status;
		this.price = price;
	}

	@Basic
	@Column(name = "room_number", nullable = false)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Basic
	@Column(name = "room_price", nullable = false)
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Basic
	@Column(name = "room_roomstatus", nullable = false)
	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	@Basic
	@Column(name = "room_capacity", nullable = false)
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Basic
	@Column(name = "room_roomstar", nullable = false)
	public RoomStar getStar() {
		return star;
	}

	public void setStar(RoomStar star) {
		this.star = star;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());

		builder.append(getNumber()).append(SEPARATOR);
		builder.append(getCapacity()).append(SEPARATOR);
		builder.append(getStar()).append(SEPARATOR);
		builder.append(getStatus()).append(SEPARATOR);
		builder.append(getPrice()).append(SEPARATOR);

		return builder.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
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

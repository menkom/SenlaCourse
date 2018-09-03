package com.senla.hotel.model;

import java.io.Serializable;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;

@CsvEntity(filename = "room.csv")
public class Room extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9218560100890176515L;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private Integer number;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private Integer capacity;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private RoomStar star;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private RoomStatus status;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

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


}

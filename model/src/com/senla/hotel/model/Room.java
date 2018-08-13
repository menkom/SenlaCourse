package com.senla.hotel.model;

import java.io.Serializable;

import com.senla.base.BaseObject;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;

public class Room extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9218560100890176515L;
	private Integer number;
	private Integer capacity;
	private RoomStar star;
	private RoomStatus status;
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

}

package com.senla.hotel.model;

import com.senla.base.BaseObject;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;

public class Room extends BaseObject {
	private int number;
	private int capacity;
	private RoomStar star;
	private RoomStatus status;
	private int price;

	public Room(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		super();
		this.number = number;
		this.capacity = capacity;
		this.star = star;
		this.status = status;
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public RoomStar getStar() {
		return star;
	}

	public void setStar(RoomStar star) {
		this.star = star;
	}

	public String toString() {
		String result = "";

		result.concat(super.toString());

		result.concat(getNumber() + BaseObject.SEPARATOR);
		result.concat(getCapacity() + BaseObject.SEPARATOR);
		result.concat(getStar() + BaseObject.SEPARATOR);
		result.concat(getStatus() + BaseObject.SEPARATOR);
		result.concat(getPrice() + BaseObject.SEPARATOR);

		return result;
	}

}

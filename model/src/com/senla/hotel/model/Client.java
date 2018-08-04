package com.senla.hotel.model;

import com.senla.base.BaseObject;

public class Client extends BaseObject {

	private String name;

	public Client(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(super.toString()).append(getName() + SEPARATOR);

		return builder.toString();
	}
}

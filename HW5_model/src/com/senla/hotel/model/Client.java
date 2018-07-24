package com.senla.hotel.model;

import com.senla.base.BaseObject;

public class Client extends BaseObject {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Client(String name) {
		super();
		this.name = name;
	}

	public String toString() {
		String result = super.toString();

		result = result.concat(getName() + BaseObject.SEPARATOR);

		return result;
	}
}

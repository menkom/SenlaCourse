package com.senla.hotel.model;

import com.senla.base.BaseObject;

public class Service extends BaseObject {

	private int code;
	private String name;
	private int price;

	public int getPrice() {
		return price;
	}

	public Service(int code, String name, int price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String toString() {
		// TODO Reformat according to changes
		String result = "";

		result.concat(super.toString());

		result.concat(getName() + BaseObject.SEPARATOR);
		result.concat(getPrice() + BaseObject.SEPARATOR);

		return result;
	}

}

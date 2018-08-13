package com.senla.hotel.model;

import java.io.Serializable;

import com.senla.base.BaseObject;

public class Service extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7596450064115664493L;

	private Integer code;
	private String name;
	private Integer price;

	public Service() {
		super();
	}

	public Service(Integer code, String name, Integer price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());

		builder.append(getCode()).append(SEPARATOR);
		builder.append(getName()).append(SEPARATOR);
		builder.append(getPrice()).append(SEPARATOR);

		return builder.toString();
	}
}

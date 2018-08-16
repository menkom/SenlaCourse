package com.senla.hotel.model;

import java.io.Serializable;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;

@CsvEntity(filename = "service.csv")
public class Service extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7596450064115664493L;

	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private Integer code;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String name;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
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

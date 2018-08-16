package com.senla.hotel.model;

import java.io.Serializable;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;

@CsvEntity(filename = "client.csv")
public class Client extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8734779988902631985L;

	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private String name;

	public Client() {
		super();
	}

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

		builder.append(super.toString()).append(getName()).append(SEPARATOR);

		return builder.toString();
	}
}

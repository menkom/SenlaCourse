package com.senla.base;

public class BaseObject {

	private Integer id;

	public static final String SEPARATOR = ";";

	public String toString() {
		return getId() + SEPARATOR;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

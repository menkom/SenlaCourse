package com.senla.base;

import java.io.Serializable;

public class BaseObject implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6399139948319588392L;

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

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}

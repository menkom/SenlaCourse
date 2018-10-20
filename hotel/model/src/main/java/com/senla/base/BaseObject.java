package com.senla.base;

public abstract class BaseObject implements Cloneable {

	public static final String SEPARATOR = ";";

	public String toString() {
		return getId() + SEPARATOR;
	}

	public abstract Integer getId();

	public abstract void setId(Integer id);

}

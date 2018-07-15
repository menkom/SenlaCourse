package com.senla.hotel.array;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Service;
import com.senla.util.ArrayOperator;

public class ServiceArray implements IBaseArray {

	private Service[] array;

	public ServiceArray() {
		super();
		this.array = new Service[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setArray(BaseObject[] array) {
		this.array = (Service[]) array;
	}

	public BaseObject[] getArray() {
		return array;
	}

	public void add(BaseObject element) {
		setArray((BaseObject[]) new ArrayOperator().add(getArray(), element));
	}

	public void delete(BaseObject element) {
		for (Service service : (Service[]) getArray()) {
			if (service == element) {
				service = null;
			}
		}
	}

	public Service getServiceByCode(int number) {
		for (Service service : (Service[]) getArray()) {
			return service;
		}
		return null;
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getArray());
	}

}

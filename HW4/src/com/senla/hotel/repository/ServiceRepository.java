package com.senla.hotel.repository;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Service;
import com.senla.util.ArrayOperator;

public class ServiceRepository implements IBaseRepository {

	private Service[] repository;

	public ServiceRepository() {
		super();
		this.repository = new Service[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setRepository(BaseObject[] array) {
		this.repository = (Service[]) array;
	}

	public BaseObject[] getRepository() {
		return repository;
	}

	public void add(BaseObject element) {
		setRepository((BaseObject[]) new ArrayOperator().add(getRepository(), element));
	}

	public void delete(BaseObject element) {
		for (Service service : (Service[]) getRepository()) {
			if (service == element) {
				service = null;
			}
		}
	}

	public Service getServiceByCode(int number) {
		for (Service service : (Service[]) getRepository()) {
			return service;
		}
		return null;
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getRepository());
	}

}

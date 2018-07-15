package com.senla.hotel.array;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.util.ArrayOperator;

public class ClientArray implements IBaseArray {

	private Client[] array;

	public ClientArray() {
		super();
		this.array = new Client[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setArray(BaseObject[] array) {
		this.array = (Client[]) array;
	}

	public BaseObject[] getArray() {
		return array;
	}

	public void add(BaseObject element) {
		setArray((BaseObject[]) new ArrayOperator().add(getArray(), element));
	}

	public void delete(BaseObject element) {
		for (Client client : (Client[]) getArray()) {
			if (client == element) {
				client = null;
			}
		}
	}

	public Client getClientByName(String name) {
		for (Client client : (Client[]) getArray()) {
			if (client.getName().equals(name)) {
				return client;
			}
		}
		return null;
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getArray());
	}

}

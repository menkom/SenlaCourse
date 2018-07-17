package com.senla.hotel.repository;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.util.ArrayOperator;

public class ClientRepository implements IBaseRepository {

	private Client[] repository;

	public ClientRepository() {
		super();
		this.repository = new Client[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setRepository(BaseObject[] array) {
		this.repository = (Client[]) array;
	}

	public BaseObject[] getRepository() {
		return repository;
	}

	public void add(BaseObject element) {
		setRepository((BaseObject[]) ArrayOperator.add(getRepository(), element));
	}

	public void delete(BaseObject element) {
		for (Client client : (Client[]) getRepository()) {
			if (client == element) {
				client = null;
			}
		}
	}

	public Client getClientByName(String name) {
		for (Client client : (Client[]) getRepository()) {
			if ((client!=null)&&(client.getName().equals(name))) {
				return client;
			}
		}
		return null;
	}

	public String[] toStringArray() {
		return ArrayOperator.toStringArray(getRepository());
	}

}

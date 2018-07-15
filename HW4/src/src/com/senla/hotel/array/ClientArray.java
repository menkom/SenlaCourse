package com.senla.hotel.array;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.util.ArrayOperator;
import com.senla.util.DispayOperator;

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
		new DispayOperator().printMessage("ClientArray. Add element");
		new DispayOperator().printMessage("ClientArray. element>" + element.toString());

		new ArrayOperator().add(getArray(), element);

		new DispayOperator().printMessage("ClientArray. Added element");
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
			if (client.getName() == name) {
				return client;
			}
		}
		return null;
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getArray());
	}

}

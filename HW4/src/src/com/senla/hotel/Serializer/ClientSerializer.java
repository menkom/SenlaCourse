package com.senla.hotel.Serializer;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;

public class ClientSerializer extends ABaseSerializer {

	public BaseObject getFromArray(String[] array) {
		return new Client(array[0]);
	}

	public BaseObject getFromString(String line) {
		return getFromArray(stringToArray(line));
	}

	public BaseObject[] getArrayFromArray(String[] arg) {
		Client[] result = new Client[arg.length];

		for (int i = 0; i < arg.length; i++) {
			result[i] = (Client) getFromString(arg[i]);
		}

		return result;
	}

}

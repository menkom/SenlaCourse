package com.senla.hotel.Serializer;

import com.senla.base.BaseObject;

public abstract class ABaseSerializer {

	public final String SEPARATOR = ";";

	public String[] stringToArray(String aValue) {
		return aValue.split(SEPARATOR);
	}

	public String[] arrayToStringArray(BaseObject[] array) {
		String[] result = new String[array.length];
		
		// TODO Check how will it work without one element inside of array
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				result[i] = array[i].toString();
			}
		}
		return result;
	}

	public abstract BaseObject getFromArray(String[] array);

	public abstract BaseObject getFromString(String line);

	public abstract BaseObject[] getArrayFromArray(String[] arg);

}

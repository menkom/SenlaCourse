package com.senla.hotel.array;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Room;
import com.senla.util.ArrayOperator;

public class RoomArray implements IBaseArray {

	private Room[] array;

	public RoomArray() {
		super();
		this.array = new Room[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setArray(BaseObject[] array) {
		this.array = (Room[]) array;
	}

	public BaseObject[] getArray() {
		return array;
	}

	public void add(BaseObject element) {
		setArray((BaseObject[]) new ArrayOperator().add(getArray(), element));
	}

	public void delete(BaseObject element) {
		for (Room room : (Room[]) getArray()) {
			if (room == element) {
				room = null;
			}
		}
	}

	public Room getRoomByNum(int number) {
		for (Room room : (Room[]) getArray()) {
			return room;
		}
		return null;
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getArray());
	}

}

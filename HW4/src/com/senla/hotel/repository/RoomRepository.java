package com.senla.hotel.repository;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Room;
import com.senla.util.ArrayOperator;

public class RoomRepository implements IBaseRepository {

	private Room[] repository;

	public RoomRepository() {
		super();
		this.repository = new Room[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setRepository(BaseObject[] array) {
		this.repository = (Room[]) array;
	}

	public BaseObject[] getRepository() {
		return repository;
	}

	public void add(BaseObject element) {
		setRepository((BaseObject[]) new ArrayOperator().add(getRepository(), element));
	}

	public void delete(BaseObject element) {
		for (Room room : (Room[]) getRepository()) {
			if (room == element) {
				room = null;
			}
		}
	}

	public Room getRoomByNum(int number) {
		for (Room room : (Room[]) getRepository()) {
			return room;
		}
		return null;
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getRepository());
	}

}

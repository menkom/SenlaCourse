package com.senla.util;

import java.util.List;

import com.senla.base.BaseObject;

public class IdGenerator {

	public static Integer generateId(Integer lastId) {
		return lastId + 1;
	}

	public static <T extends BaseObject> Integer getLastId(List<T> list) {
		Integer max = 0;
		for (BaseObject object : list) {
			max = Math.max(max, object.getId());
		}
		return max;
	}
}

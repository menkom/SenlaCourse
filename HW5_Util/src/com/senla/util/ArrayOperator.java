package com.senla.util;

import java.util.Arrays;

public class ArrayOperator {

	public static final int MINIMUM_ARRAY_LENGTH = 0;

	public static boolean isFreeSpace(Object[] array) {
		boolean result = false;

		for (Object obj : array) {
			if (obj == null) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static int getFreeIndex(Object[] array) {
		int result = -1;
		for (int i = 0; i < array.length; i++)
			if (array[i] == null) {
				result = i;
				break;
			}
		return result;
	}

	public static Object[] add(Object[] array, Object element) {
		Object[] result;

		if (!isFreeSpace(array)) {
			int newLength = array.length + 1;
			result = Arrays.copyOf(array, newLength);
		} else {
			result = array;
		}
		result[getFreeIndex(result)] = element;

		return result;
	}

}
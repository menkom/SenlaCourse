package com.senla.util;

import java.util.Arrays;

import com.senla.base.BaseObject;

public class ArrayOperator {

	public static final int MINIMUM_ARRAY_LENGTH = 0;

	public boolean isFreeSpace(Object[] array) {
		boolean result = false;

		for (Object obj : array) {
			if (obj == null) {
				result = true;
				break;
			}
		}

		return result;
	}

	public int getFreeIndex(Object[] array) {
		int result = -1;
		for (int i = 0; i < array.length; i++)
			if (array[i] == null) {
				result = i;
				break;
			}
		return result;
	}

	public Object[] add(Object[] array, Object element) {
		Object[] result;

		if (!isFreeSpace(array)) {
			result = Arrays.copyOf(array, array.length + 1);
		} else {
			result = array;
		}
		result[getFreeIndex(result)] = element;

		return result;
	}

	public int getElementsCount(Object[] array) {
		int result = 0;
		for (Object element : array) {
			if (element != null) {
				result++;
			}
		}
		return result;
	}

	public String[] toStringArray(BaseObject[] array) {

		String[] result = new String[getElementsCount(array)];

		// TODO What about empty elements? Should we save them? If we save them we have
		// problem on load
		int i = 0;

		for (BaseObject element : array) {
			if (element != null) {
				result[i] = element.toString();
				i++;
			}
		}
		return result;
	}

}
package com.senla.util;

public class StringParser {

	public static final String SEPARATOR = ";";

	public static String[] stringToArray(String aValue) {
		return aValue.split(SEPARATOR);
	}

}

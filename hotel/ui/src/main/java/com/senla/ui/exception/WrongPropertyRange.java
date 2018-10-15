package com.senla.ui.exception;

@SuppressWarnings("serial")
public class WrongPropertyRange extends Exception {

	private static final String NOT_IN_RANGE = "Current value [%s] is not in property range.";
	private int wrongValue;

	public WrongPropertyRange(int wrongValue) {
		this.wrongValue = wrongValue;
	}

	public String toString() {
		return String.format(NOT_IN_RANGE, wrongValue);
	}

}

package com.senla.exception;

@SuppressWarnings("serial")
public class NoEntryException extends Exception {

	private String lookEntry;

	public NoEntryException(String arg0) {
		lookEntry = arg0;
	}

	public String toString() {
		return "No such entry in DB [" + lookEntry + "]";
	}

}

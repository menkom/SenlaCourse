package com.senla.exception;

@SuppressWarnings("serial")
public class NoEntryException extends Exception {

	private static final String NO_ENTRY = "No such entry in DB [%s]";
	private String lookEntry;

	public NoEntryException(String arg0) {
		lookEntry = arg0;
	}

	public String toString() {
		return String.format(NO_ENTRY, lookEntry);
	}

}

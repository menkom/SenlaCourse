package com.senla.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperator {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	private SimpleDateFormat formatter;

	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public SimpleDateFormat getSimpleDateFormat() {
		if (formatter == null) {
			formatter = new SimpleDateFormat(DATE_FORMAT);
		}
		return formatter;
	}

	public Date getStringToDate(String str) throws ParseException {
		return (str.equals("null") ? null : new DateOperator().getSimpleDateFormat().parse(str));
	}

	public String getDateToString(Date date) {
		return new DateOperator().getSimpleDateFormat().format(date);
	}
}

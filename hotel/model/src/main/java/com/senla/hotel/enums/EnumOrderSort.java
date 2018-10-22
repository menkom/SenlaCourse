package com.senla.hotel.enums;

public enum EnumOrderSort {

	ID("id"), CLIENT_NAME("name"), FINISH_DATE("finishDate");

	private String tableField;

	EnumOrderSort(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return tableField;
	}

}

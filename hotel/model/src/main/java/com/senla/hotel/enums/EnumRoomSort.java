package com.senla.hotel.enums;

public enum EnumRoomSort {
	ID("id"), CAPACITY("capacity"), STAR("roomStar"), PRICE("price");
	private String tableField;

	EnumRoomSort(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return tableField;
	}
}

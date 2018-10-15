package com.senla.hotel.enums;

public enum EnumRoomSort {
	ID("room_id"), CAPACITY("room_capacity"), STAR("room_roomstar"), PRICE("room_price");
	private String tableField;

	EnumRoomSort(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return tableField;
	}
}

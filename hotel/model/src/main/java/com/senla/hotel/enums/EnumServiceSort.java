package com.senla.hotel.enums;

public enum EnumServiceSort {
	ID("id"), CODE("code"), NAME("name"), PRICE("price");
	private String tableField;

	EnumServiceSort(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return tableField;
	}

}

package com.senla.hotel.enums;

public enum EnumServiceSort {
	ID("service_id"), CODE("service_code"), NAME("service_name"), PRICE("service_price");
	private String tableField;

	EnumServiceSort(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return tableField;
	}

}

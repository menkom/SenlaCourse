package com.senla.hotel.enums;

public enum EnumOrderSort {
	
	ID("order_id"), CLIENT_NAME("client_name"), FINISH_DATE("order_finish_date");
	
	private String tableField;

	EnumOrderSort(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return tableField;
	}

}

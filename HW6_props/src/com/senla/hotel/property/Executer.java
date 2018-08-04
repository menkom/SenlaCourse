package com.senla.hotel.property;

import com.senla.util.DisplayOperator;

public class Executer {

	public static void main(String[] args) {
		DisplayOperator.printMessage("Properties for application: " + HotelProperty.getInstance().getName()
				+ ". isAbleChangeRoomStatus=" + HotelProperty.getInstance().isAbleChangeRoomStatus()
				+ ". lastVisibleOrders= " + HotelProperty.getInstance().getLastVisibleOrders()+".");

	}

}

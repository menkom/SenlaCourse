package com.senla.hotel.property;

public class Executer {

	public static void main(String[] args) {
		System.out.println("Properties for application: " + HotelProperty.getInstance().getName()
				+ ". isAbleChangeRoomStatus=" + HotelProperty.getInstance().isAbleChangeRoomStatus()
				+ ". lastVisibleOrders= " + HotelProperty.getInstance().getLastVisibleOrders() + ".");

	}

}

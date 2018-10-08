package com.senla.dao.test;

public class Executer {

	public static void main(String[] args) {
		try {
			ClientTest.testClientDao();
//			RoomTest.testRoomDao();
//			ServiceTest.testServiceDao();
//			OrderTest.testOrderDao();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
}

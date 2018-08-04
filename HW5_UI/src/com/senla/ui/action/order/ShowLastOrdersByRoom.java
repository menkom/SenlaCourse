package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.property.HotelProperty;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowLastOrdersByRoom implements IAction {

	private static final Logger logger = Logger.getLogger(ShowLastOrdersByRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int roomNum = 0;
		DisplayOperator.printMessage("Enter room num: ");
		try {
			roomNum = scanner.nextInt();

			DisplayOperator.printOrders(

					Hotel.getInstance().getLastOrdersByRoom(roomNum,
							HotelProperty.getInstance().getLastVisibleOrders()));
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("You need to enter room number.");
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage("Order #" + Integer.toString(roomNum) + " not found.");
			logger.error(e.toString());
		}

	}

}

package com.senla.ui.action.room;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class FreeRoom implements IAction {

	private static final Logger logger = Logger.getLogger(FreeRoom.class);

	@Override
	public void execute() {
		try (Scanner scanner = new Scanner(System.in)) {
			int orderNum = 0;
			DisplayOperator.printMessage("Enter order num: ");
			try {
				orderNum = scanner.nextInt();

				boolean result = Hotel.getInstance().freeRoom(orderNum);

				if (result) {
					DisplayOperator.printMessage("Order #" + orderNum + " was succesfully closed.");
				} else {
					DisplayOperator.printMessage("Error accured during closing Order #" + orderNum + ".");
				}
			} catch (InputMismatchException e) {
				DisplayOperator.printMessage("You need to enter order number.");
				logger.error(e.toString());
			} catch (NullPointerException e) {
				DisplayOperator.printMessage("Order #" + Integer.toString(orderNum) + " not found.");
				logger.error(e.toString());
			}
		}
	}

}

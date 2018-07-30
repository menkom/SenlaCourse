package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowOrderServices implements IAction {

	private static final Logger logger = Logger.getLogger(ShowOrderServices.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int orderNum = 0;
		DisplayOperator.printMessage("Enter order num: ");
		try {
			orderNum = scanner.nextInt();

			DisplayOperator.printServices(Hotel.getInstance().getOrderServices(orderNum));
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("You need to enter order number.");
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage("Order #" + Integer.toString(orderNum) + " not found.");
			logger.error(e.toString());
		}

	}

}

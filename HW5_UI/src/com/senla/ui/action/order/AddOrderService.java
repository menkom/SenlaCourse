package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.action.room.AddRoom;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class AddOrderService implements IAction {

	private static final Logger logger = Logger.getLogger(AddRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int orderNum = -1;
		int serviceCode = -1;

		try {
			DisplayOperator.printMessage("Enter order num: ");
			orderNum = scanner.nextInt();
			DisplayOperator.printMessage("Enter service code: ");
			serviceCode = scanner.nextInt();

			Hotel.getInstance().addOrderService(orderNum, serviceCode);
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("Input correct field types.");
			logger.error(e);
		}

	}

}

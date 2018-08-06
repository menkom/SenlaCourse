package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class AddOrderService implements IAction {

	private static final String ENTER_ORDER_NUM = "Enter order num: ";
	private static final String ENTER_SERVICE_CODE = "Enter service code: ";
	private static final String SERVICE_ADDED = "Service with code %s added to order #%s.";
	private static final String ERROR_ADDING_SERVICE = "Error during adding service to order.";
	private static final String ERROR_IN_FIELDS = "Input correct field types.";

	private static final Logger logger = Logger.getLogger(AddOrderService.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int orderNum = -1;
		int serviceCode = -1;

		try {
			DisplayOperator.printMessage(ENTER_ORDER_NUM);
			orderNum = Integer.parseInt(scanner.nextLine());
			DisplayOperator.printMessage(ENTER_SERVICE_CODE);
			serviceCode = Integer.parseInt(scanner.nextLine());

			Boolean result = Hotel.getInstance().addOrderService(orderNum, serviceCode);

			if (result) {
				DisplayOperator.printMessage(String.format(SERVICE_ADDED, serviceCode, orderNum));
			} else {
				DisplayOperator.printMessage(ERROR_ADDING_SERVICE);
			}
		} catch (InputMismatchException|NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		} 

	}

}

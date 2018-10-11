package com.senla.ui.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class AddOrderService implements IAction {

	private static final String ENTER_ORDER_ID = "Enter order Id: ";
	private static final String ENTER_SERVICE_ID = "Enter service Id: ";
	private static final String SERVICE_ADDED = "Service with Id %s added to order Id %s.";
	private static final String ERROR_ADDING_SERVICE = "Error during adding service to order.";
	private static final String ERROR_IN_FIELDS = "Input correct field types.";

	private static final Logger logger = Logger.getLogger(AddOrderService.class);

	@Override
	public void execute() {

		try {
			DisplayOperator.printMessage(ENTER_ORDER_ID);
			Integer orderId = Input.inputInteger();
			DisplayOperator.printMessage(ENTER_SERVICE_ID);
			Integer serviceId = Input.inputInteger();

			Boolean result = Hotel.getInstance().addOrderService(orderId, serviceId);

			if (result) {
				DisplayOperator.printMessage(String.format(SERVICE_ADDED, serviceId, orderId));
			} else {
				DisplayOperator.printMessage(ERROR_ADDING_SERVICE);
			}
		} catch (InputMismatchException | NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}

	}

}

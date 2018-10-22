package com.senla.ui.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class AddOrderService implements IAction {

	private static final String ENTER_ORDER_ID = "Enter order Id: ";
	private static final String ENTER_SERVICE_ID = "Enter service Id: ";
	private static final String ERROR_IN_FIELDS = "Input correct field types.";

	private static final Logger logger = Logger.getLogger(AddOrderService.class);

	@Override
	public void execute() {

		try {
			DisplayOperator.printMessage(ENTER_ORDER_ID);
			Integer orderId = Input.inputInteger();
			Order order = Hotel.getInstance().getOrderById(orderId);

			DisplayOperator.printMessage(ENTER_SERVICE_ID);
			Integer serviceId = Input.inputInteger();
			Service service = Hotel.getInstance().getServiceById(serviceId);

			Hotel.getInstance().addOrderService(order, service);

		} catch (InputMismatchException | NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}

	}

}

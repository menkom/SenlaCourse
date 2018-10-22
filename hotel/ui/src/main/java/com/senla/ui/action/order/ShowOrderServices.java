package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ShowOrderServices implements IAction {

	private static final String ENTER_ORDER_ID = "Enter order Id: ";
	private static final String ERROR_NEED_ORDER = "You need to enter order Id.";
	private static final String ERROR_ORDER_ID = "Order with Id %s not found.";
	private static final String ERROR_SERVICES_SEARCH = "Error during services search.";
	private static final String NO_SERVICES = "No services.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ShowOrderServices.class);

	@Override
	public void execute() {
		int orderId = 0;
		DisplayOperator.printMessage(ENTER_ORDER_ID);
		try {
			orderId = Input.inputInteger();

			Order order = Hotel.getInstance().getOrderById(orderId);
			List<Service> services = Hotel.getInstance().getOrderServices(order);

			if (services == null) {
				DisplayOperator.printMessage(ERROR_SERVICES_SEARCH);
			} else {
				if (services.size() > 0) {
					DisplayOperator.printServices(services);
				} else {
					DisplayOperator.printMessage(NO_SERVICES);
				}
			}
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_NEED_ORDER);
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage(String.format(ERROR_ORDER_ID, orderId));
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}
	}

}

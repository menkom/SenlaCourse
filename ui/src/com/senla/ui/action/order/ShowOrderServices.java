package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Service;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowOrderServices implements IAction {

	private static final String ENTER_ORDER_NUM = "Enter order num: ";
	private static final String ERROR_NEED_ORDER = "You need to enter order number.";
	private static final String ERROR_ORDER_NUM = "Order #%s not found.";
	private static final String ERROR_SERVICES_SEARCH = "Error during services search.";
	private static final String NO_SERVICES = "No services.";

	private static final Logger logger = Logger.getLogger(ShowOrderServices.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int orderNum = 0;
		DisplayOperator.printMessage(ENTER_ORDER_NUM);
		try {
			orderNum = Integer.parseInt(scanner.nextLine());

			List<Service> services = Hotel.getInstance().getOrderServices(orderNum);

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
			DisplayOperator.printMessage(String.format(ERROR_ORDER_NUM, orderNum));
			logger.error(e.toString());
		}
	}

}

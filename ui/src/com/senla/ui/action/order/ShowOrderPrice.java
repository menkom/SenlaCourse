package com.senla.ui.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ShowOrderPrice implements IAction {

	private static final String ENTER_ORDER_NUM = "Enter order num: ";
	private static final String ERROR_NEED_ORDER = "You need to enter order number.";
	private static final String ERROR_ORDER_NUM = "Order #%s not found.";
	private static final String ERROR_PRICE_CALC = "Error during price calculation.";
	private static final String ORDER_PRICE = "Price for order #%s %s";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ShowOrderPrice.class);

	@Override
	public void execute() {
		int orderNum = 0;
		DisplayOperator.printMessage(ENTER_ORDER_NUM);
		try {
			orderNum = Input.inputInteger();

			Integer orderPrice = Hotel.getInstance().getOrderPrice(orderNum);

			if (orderPrice == null) {
				DisplayOperator.printMessage(ERROR_PRICE_CALC);
			} else {
				DisplayOperator.printMessage(String.format(ORDER_PRICE, orderNum, orderPrice));
			}

		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_NEED_ORDER);
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage(String.format(ERROR_ORDER_NUM, orderNum));
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}
	}

}

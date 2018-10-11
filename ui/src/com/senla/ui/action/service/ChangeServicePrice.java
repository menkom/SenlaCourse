package com.senla.ui.action.service;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.exception.WrongPropertyRange;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ChangeServicePrice implements IAction {

	private static final String ENTER_SERVICE_ID = "Enter service Id: ";
	private static final String ENTER_SERVICE_NEW_PRICE = "Enter service new price: ";
	private static final String PRICE_CHANGED = "Service with Id %s price changed to %s successfully.";
	private static final String ERROR_CHANGING_PRICE = "Error changing price.";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ChangeServicePrice.class);

	@Override
	public void execute() {
		int serviceId = 0;
		int price = -1;

		try {
			DisplayOperator.printMessage(ENTER_SERVICE_ID);
			serviceId = Input.inputInteger();
			DisplayOperator.printMessage(ENTER_SERVICE_NEW_PRICE);
			price = Input.inputInteger();
			if (price < 0) {
				throw new WrongPropertyRange(price);
			}

			Boolean result = Hotel.getInstance().changeServicePrice(serviceId, price);
			if (result) {
				DisplayOperator.printMessage(String.format(PRICE_CHANGED, serviceId, price));
			} else {
				DisplayOperator.printMessage(ERROR_CHANGING_PRICE);
			}

		} catch (NumberFormatException | InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
			logger.error(e);
		} catch (WrongPropertyRange e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}
	}

}

package com.senla.ui.action.service;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.exception.WrongPropertyRange;
import com.senla.ui.util.DisplayOperator;
import com.senla.ui.util.Input;

public class AddService implements IAction {
	private static final String ENTER_SERVICE_CODE = "Enter service code: ";
	private static final String ENTER_SERVICE_NAME = "Enter service name: ";
	private static final String ENTER_SERVICE_PRICE = "Enter service price: ";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(AddService.class);

	@Override
	public void execute() {
		int serviceCode = -1;
		String serviceName = "";
		int price = -1;

		try {
			DisplayOperator.printMessage(ENTER_SERVICE_CODE);
			serviceCode = Input.inputInteger();
			DisplayOperator.printMessage(ENTER_SERVICE_NAME);
			serviceName = Input.inputString();
			DisplayOperator.printMessage(ENTER_SERVICE_PRICE);
			price = Input.inputInteger();
			if (price < 0) {
				throw new WrongPropertyRange(price);
			}

			Hotel.getInstance().addService(serviceCode, serviceName, price);

		} catch (NumberFormatException | InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
			logger.error(e);
		} catch (WrongPropertyRange e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}
	}

}

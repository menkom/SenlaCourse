package com.senla.ui.action.service;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.exception.WrongPropertyRange;
import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.util.DisplayOperator;

public class AddService implements IAction {
	private static final String ENTER_SERVICE_CODE = "Enter service code: ";
	private static final String ENTER_SERVICE_NAME = "Enter service name: ";
	private static final String ENTER_SERVICE_PRICE = "Enter service price: ";
	private static final String SERVICE_ADDED = "Service #%s added successfully.";
	private static final String ERROR_CREATING_SERVICE = "Error creating service.";
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

			Boolean result = Hotel.getInstance().addService(serviceCode, serviceName, price);
			if (result) {
				DisplayOperator.printMessage(String.format(SERVICE_ADDED, serviceCode));
			} else {
				DisplayOperator.printMessage(ERROR_CREATING_SERVICE);
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

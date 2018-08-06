package com.senla.ui.action.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.exception.WrongPropertyRange;
import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ChangeServicePrice implements IAction {

	private static final String ENTER_SERVICE_CODE = "Enter service code: ";
	private static final String ENTER_SERVICE_NEW_PRICE = "Enter service new price: ";
	private static final String PRICE_CHANGED = "Service #%s price changed to %s successfully.";
	private static final String ERROR_CHANGING_PRICE = "Error changing price.";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ChangeServicePrice.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int serviceCode = -1;
		int price = -1;

		try {
			DisplayOperator.printMessage(ENTER_SERVICE_CODE);
			serviceCode = Integer.parseInt(scanner.nextLine());
			DisplayOperator.printMessage(ENTER_SERVICE_NEW_PRICE);
			price = Integer.parseInt(scanner.nextLine());
			if (price < 0) {
				throw new WrongPropertyRange(price);
			}

			Boolean result = Hotel.getInstance().changeServicePrice(serviceCode, price);
			if (result) {
				DisplayOperator.printMessage(String.format(PRICE_CHANGED, serviceCode, price));
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

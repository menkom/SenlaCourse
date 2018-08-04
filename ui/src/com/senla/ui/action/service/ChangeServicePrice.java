package com.senla.ui.action.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ChangeServicePrice implements IAction {

	private static final Logger logger = Logger.getLogger(ChangeServicePrice.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int serviceCode = -1;
		int price = -1;

		try {
			DisplayOperator.printMessage("Enter service code: ");
			serviceCode = scanner.nextInt();
			DisplayOperator.printMessage("Enter new price: ");
			price = scanner.nextInt();

			Hotel.getInstance().changeServicePrice(serviceCode, price);
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("Input correct field types.");
			logger.error(e);
		}
	}

}

package com.senla.ui.action.client;

import java.util.Scanner;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ExportClientAction implements IAction {

	private static final String ENTER_CLIENT_NAME = "Enter client name to export: ";
	private static final String CLIENT_EXPORTED = "Client %s exported.";
	private static final String CLIENT_EXPORT_FAILED = "Error during client export.";

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		DisplayOperator.printMessage(ENTER_CLIENT_NAME);
		String clientName = scanner.nextLine();

		Boolean result = Hotel.getInstance().exportClientCSV(clientName);
		if (result) {
			DisplayOperator.printMessage(String.format(CLIENT_EXPORTED, clientName));
		} else {
			DisplayOperator.printMessage(CLIENT_EXPORT_FAILED);
		}
	}

}

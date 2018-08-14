package com.senla.ui.action.client;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.util.DisplayOperator;

public class ExportClientAction implements IAction {

	private static final String ENTER_CLIENT_NAME = "Enter client name to export: ";
	private static final String ENTER_FILE_NAME = "Enter file name to export (client_ClientName.csv if empty by default): ";
	private static final String CLIENT_EXPORTED = "Client %s exported.";
	private static final String CLIENT_EXPORT_FAILED = "Error during client export.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_CLIENT_NAME);
		String clientName = Input.inputString();

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();

		Boolean result = Hotel.getInstance().exportClientCSV(clientName, fileName);
		if (result) {
			DisplayOperator.printMessage(String.format(CLIENT_EXPORTED, clientName));
		} else {
			DisplayOperator.printMessage(CLIENT_EXPORT_FAILED);
		}
	}

}

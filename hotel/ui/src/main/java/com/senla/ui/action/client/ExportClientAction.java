package com.senla.ui.action.client;

import com.senla.di.DependencyInjection;
import com.senla.hotel.facade.api.IHotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ExportClientAction implements IAction {

	private static final String ENTER_CLIENT_ID = "Enter client Id to export: ";
	private static final String ENTER_FILE_NAME = "Enter file name to export (client_ClientName.csv if empty by default): ";
	private static final String CLIENT_EXPORTED = "Client %s exported.";
	private static final String CLIENT_EXPORT_FAILED = "Error during client export.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_CLIENT_ID);
		int clientId = Input.inputInteger();

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();

		IHotel hotel = DependencyInjection.getInstance().getInterfacePair(IHotel.class);
		Boolean result = hotel.exportClientCSV(clientId, fileName);

		if (result) {
			DisplayOperator.printMessage(String.format(CLIENT_EXPORTED, clientId));
		} else {
			DisplayOperator.printMessage(CLIENT_EXPORT_FAILED);
		}
	}

}

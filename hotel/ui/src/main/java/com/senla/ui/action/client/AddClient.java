package com.senla.ui.action.client;

import com.senla.di.DependencyInjection;
import com.senla.hotel.facade.api.IHotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.DisplayOperator;
import com.senla.ui.util.Input;

public class AddClient implements IAction {

	private static final String ENTER_CLIENT_NAME = "Enter new client name: ";
	private static final String CLIENT_CREATED = "New client %s added.";
	private static final String CLIENT_CREATION_FAILED = "Error during client creation.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_CLIENT_NAME);
		String clientName = Input.inputString();

		IHotel hotel = DependencyInjection.getInstance().getInterfacePair(IHotel.class);

		Boolean result = hotel.addClient(clientName);
		if (result) {
			DisplayOperator.printMessage(String.format(CLIENT_CREATED, clientName));
		} else {
			DisplayOperator.printMessage(CLIENT_CREATION_FAILED);
		}
	}

}

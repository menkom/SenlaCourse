package com.senla.ui.action.client;

import java.util.Scanner;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class AddClient implements IAction {

	private static final String ENTER_CLIENT_NAME = "Enter new client name: ";
	private static final String CLIENT_CREATED = "New client %s added.";
	private static final String CLIENT_CREATION_FAILED = "Error during client creation.";

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		DisplayOperator.printMessage(ENTER_CLIENT_NAME);
		String clientName = scanner.nextLine();

		Boolean result = Hotel.getInstance().addClient(clientName);
		if (result) {
			DisplayOperator.printMessage(String.format(CLIENT_CREATED, clientName));
		} else {
			DisplayOperator.printMessage(CLIENT_CREATION_FAILED);
		}
	}

}

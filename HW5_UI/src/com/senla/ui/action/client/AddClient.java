package com.senla.ui.action.client;

import java.util.Scanner;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class AddClient implements IAction {

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		DisplayOperator.printMessage("Enter new client name: ");
		String clientName = scanner.nextLine();
		Hotel.getInstance().addClient(clientName);
	}

}

package com.senla.ui.action;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Client;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowAllClients implements IAction {

	@Override
	public void execute() {
		List<Client> clients = Hotel.getInstance().getAllClients();
		if (clients.size() > 0) {
			DisplayOperator.printClients(clients);
		} else {
			DisplayOperator.printMessage("No clients found.");
		}
	}

}

package com.senla.ui.action.client;

import java.util.List;

import com.senla.di.DependencyInjection;
import com.senla.hotel.facade.api.IHotel;
import com.senla.hotel.model.Client;
import com.senla.ui.base.IAction;
import com.senla.ui.util.DisplayOperator;

public class ShowAllClients implements IAction {

	private static final String CLIENT_SEARCH_FAILED = "Error during clients search.";
	private static final String NO_CLIENTS = "No clients found.";

	@Override
	public void execute() {
		IHotel hotel = DependencyInjection.getInstance().getInterfacePair(IHotel.class);

		List<Client> clients = hotel.getAllClients();
		if (clients == null) {
			DisplayOperator.printMessage(CLIENT_SEARCH_FAILED);
		} else {
			if (clients.size() > 0) {
				DisplayOperator.printClients(clients);
			} else {
				DisplayOperator.printMessage(NO_CLIENTS);
			}
		}
	}
}

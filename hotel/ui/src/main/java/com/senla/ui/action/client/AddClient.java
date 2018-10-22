package com.senla.ui.action.client;

import com.senla.di.DependencyInjection;
import com.senla.hotel.facade.api.IHotel;
import com.senla.hotel.model.Client;
import com.senla.ui.base.IAction;
import com.senla.ui.util.DisplayOperator;
import com.senla.ui.util.Input;

public class AddClient implements IAction {

	private static final String ENTER_CLIENT_NAME = "Enter new client name: ";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_CLIENT_NAME);
		String clientName = Input.inputString();

		IHotel hotel = DependencyInjection.getInstance().getInterfacePair(IHotel.class);

		hotel.addClient(new Client(clientName));

	}

}

package com.senla.ui.action.client;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowNumberOfClients implements IAction {

	@Override
	public void execute() {
		DisplayOperator.printMessage("Number of clients : " + Hotel.getInstance().getNumberOfClients());
	}

}

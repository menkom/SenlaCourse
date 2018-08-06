package com.senla.ui.action.client;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowNumberOfClients implements IAction {

	private static final String CLIENTS_COUNTING_FAILED = "Error during clients counting.";
	private static final String NUMBER_OFCLIENTS = "Number of clients : ";

	@Override
	public void execute() {
		Integer numberClients = Hotel.getInstance().getNumberOfClients();
		if (numberClients == null) {
			DisplayOperator.printMessage(CLIENTS_COUNTING_FAILED);
		} else {
			DisplayOperator.printMessage(NUMBER_OFCLIENTS + numberClients);
		}
	}

}

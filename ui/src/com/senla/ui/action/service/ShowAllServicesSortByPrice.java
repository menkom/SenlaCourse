package com.senla.ui.action.service;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Service;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowAllServicesSortByPrice implements IAction {

	private static final String ERROR_SERVICES_SEARCH = "Error during services search.";
	private static final String NO_SERVICES = "No services found.";

	@Override
	public void execute() {
		List<Service> services = Hotel.getInstance().getAllServicesSortByPrice();
		if (services == null) {
			DisplayOperator.printMessage(ERROR_SERVICES_SEARCH);
		} else {
			if (services.size() > 0) {
				DisplayOperator.printServices(services);
			} else {
				DisplayOperator.printMessage(NO_SERVICES);

			}
		}

	}

}

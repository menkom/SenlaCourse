package com.senla.ui.action.service;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Service;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowAllServicesSortByPrice implements IAction {

	@Override
	public void execute() {
		List<Service> services = Hotel.getInstance().getAllServicesSortByPrice();
		if (services.size() > 0) {
			DisplayOperator.printServices(services);
		} else {
			DisplayOperator.printMessage("No services found.");

		}

	}

}

package com.senla.ui.action;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowActiveOrdersSortByName implements IAction {

	@Override
	public void execute() {
		List<Order> orders = Hotel.getInstance().getActiveOrdersSortByName();
		if (orders.size() > 0) {
			DisplayOperator.printOrders(orders);
		} else {
			DisplayOperator.printMessage("No orders found.");

		}

	}

}

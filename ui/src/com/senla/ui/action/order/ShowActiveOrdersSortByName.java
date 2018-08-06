package com.senla.ui.action.order;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowActiveOrdersSortByName implements IAction {

	private static final String ORDER_SEARCH_FAILED = "Error during orders search.";
	private static final String NO_ORDERS = "No orders found.";

	@Override
	public void execute() {
		List<Order> orders = Hotel.getInstance().getActiveOrdersSortByName();
		if (orders == null) {
			DisplayOperator.printMessage(ORDER_SEARCH_FAILED);
		} else {
			if (orders.size() > 0) {
				DisplayOperator.printOrders(orders);
			} else {
				DisplayOperator.printMessage(NO_ORDERS);
			}
		}
	}

}

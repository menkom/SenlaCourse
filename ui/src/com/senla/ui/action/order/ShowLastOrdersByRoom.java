package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ShowLastOrdersByRoom implements IAction {

	private static final String ENTER_ROOM_ID = "Enter room Id: ";
	private static final String ORDER_SEARCH_FAILED = "Error during orders search.";
	private static final String NO_ORDERS = "No orders found.";
	private static final String ERROR_NEED_ROOM = "You need to enter room Id.";
	private static final String ERROR_ROOM_ID = "Room Id %s not found.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ShowLastOrdersByRoom.class);

	@Override
	public void execute() {
		int roomId = 0;
		DisplayOperator.printMessage(ENTER_ROOM_ID);
		try {
			roomId = Input.inputInteger();

			List<Order> orders = Hotel.getInstance().getLastOrdersByRoom(roomId);

			if (orders == null) {
				DisplayOperator.printMessage(ORDER_SEARCH_FAILED);
			} else {
				if (orders.size() > 0) {
					DisplayOperator.printOrders(orders);
				} else {
					DisplayOperator.printMessage(NO_ORDERS);
				}
			}

		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_NEED_ROOM);
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage(String.format(ERROR_ROOM_ID, roomId));
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}

	}

}

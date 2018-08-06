package com.senla.ui.action.order;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.hotel.property.HotelProperty;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowLastOrdersByRoom implements IAction {

	private static final String ENTER_ROOM_NUM = "Enter room num: ";
	private static final String ORDER_SEARCH_FAILED = "Error during orders search.";
	private static final String NO_ORDERS = "No orders found.";
	private static final String ERROR_NEED_ROOM = "You need to enter room number.";
	private static final String ERROR_ROOM_NUM = "Room #%s not found.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ShowLastOrdersByRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int roomNum = 0;
		DisplayOperator.printMessage(ENTER_ROOM_NUM);
		try {
			roomNum = Integer.parseInt(scanner.nextLine());

			List<Order> orders = Hotel.getInstance().getLastOrdersByRoom(roomNum,
					HotelProperty.getInstance().getLastVisibleOrders());

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
			DisplayOperator.printMessage(String.format(ERROR_ROOM_NUM, roomNum));
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}

	}

}

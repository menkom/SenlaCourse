package com.senla.ui.action.room;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class FreeRoom implements IAction {

	private static final String ENTER_ROOM_ID = "Enter room Id: ";
	private static final String ERROR_NEED_ORDER = "You need to enter order Id.";
	private static final String ERROR_ORDER_ID = "Order #%s not found.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(FreeRoom.class);

	@Override
	public void execute() {
		int orderId = 0;
		DisplayOperator.printMessage(ENTER_ROOM_ID);
		try {
			orderId = Input.inputInteger();

			Hotel.getInstance().freeRoom(orderId);

		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_NEED_ORDER);
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage(String.format(ERROR_ORDER_ID, orderId));
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}
	}

}

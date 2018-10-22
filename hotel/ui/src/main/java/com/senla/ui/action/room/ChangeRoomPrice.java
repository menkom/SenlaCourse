package com.senla.ui.action.room;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.exception.WrongPropertyRange;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ChangeRoomPrice implements IAction {

	private static final String ENTER_ROOM_ID = "Enter room Id: ";
	private static final String ENTER_ROOM_PRICE = "Enter room new price: ";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ChangeRoomPrice.class);

	@Override
	public void execute() {

		try {
			DisplayOperator.printMessage(ENTER_ROOM_ID);
			int roomId = Input.inputInteger();
			DisplayOperator.printMessage(ENTER_ROOM_PRICE);
			int price = Input.inputInteger();
			if (price < 0) {
				throw new WrongPropertyRange(price);
			}

			Hotel.getInstance().changeRoomPrice(roomId, price);

		} catch (InputMismatchException | NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
			logger.error(e);
		} catch (WrongPropertyRange e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}

	}

}

package com.senla.ui.action.room;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.exception.WrongPropertyRange;
import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ChangeRoomPrice implements IAction {

	private static final String ENTER_ROOM_NUM = "Enter room num: ";
	private static final String ENTER_ROOM_PRICE = "Enter room new price: ";
	private static final String ROOM_PRICE_CHANGED = "Room #%s price changed to %s.";
	private static final String ERROR_CHANGING_PRICE = "Error changing room price.";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ChangeRoomPrice.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		try {
			DisplayOperator.printMessage(ENTER_ROOM_NUM);
			int roomNum = Integer.parseInt(scanner.nextLine());
			DisplayOperator.printMessage(ENTER_ROOM_PRICE);
			int price = Integer.parseInt(scanner.nextLine());
			if (price < 0) {
				throw new WrongPropertyRange(price);
			}

			Boolean result = Hotel.getInstance().changeRoomPrice(roomNum, price);
			if (result) {
				DisplayOperator.printMessage(String.format(ROOM_PRICE_CHANGED, roomNum, price));
			} else {
				DisplayOperator.printMessage(ERROR_CHANGING_PRICE);
			}

		} catch (InputMismatchException | NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
			logger.error(e);
		} catch (WrongPropertyRange e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}

	}

}

package com.senla.ui.action.room;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.ui.exception.WrongPropertyRange;
import com.senla.ui.util.DisplayOperator;
import com.senla.ui.util.Input;

public class AddRoom implements IAction {

	private static final String ENTER_ROOM_NUM = "Enter room num: ";
	private static final String ENTER_ROOM_CAPACITY = "Enter room capacity: ";
	private static final String ENTER_ROOM_STAR = "Enter number of stars (from 1 to 5): ";
	private static final String ENTER_ROOM_STATUS = "Enter room status (1-AVAILABLE,2-OCCUPIED,3-SERVICED): ";
	private static final String ENTER_ROOM_PRICE = "Enter room price: ";
	private static final String ERROR_FIELDS_RANGE = "Entered data is out of specified range.";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(AddRoom.class);

	@Override
	public void execute() {

		try {
			DisplayOperator.printMessage(ENTER_ROOM_NUM);
			int roomNum = Input.inputInteger();

			DisplayOperator.printMessage(ENTER_ROOM_CAPACITY);
			int capacity = Input.inputInteger();
			if (capacity < 1) {
				throw new WrongPropertyRange(capacity);
			}

			DisplayOperator.printMessage(ENTER_ROOM_STAR);
			RoomStar star = RoomStar.values()[Input.inputInteger() - 1];

			DisplayOperator.printMessage(ENTER_ROOM_STATUS);
			RoomStatus status = RoomStatus.values()[Input.inputInteger() - 1];

			DisplayOperator.printMessage(ENTER_ROOM_PRICE);
			int price = Input.inputInteger();
			if (price < 0) {
				throw new WrongPropertyRange(price);
			}

			Hotel.getInstance().addRoom(new Room(roomNum, capacity, star, status, price));

		} catch (InputMismatchException | NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
			logger.error(e);
		} catch (IndexOutOfBoundsException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_RANGE);
			logger.error(e);
		} catch (WrongPropertyRange e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}
	}

}

package com.senla.ui.action.room;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class AddRoom implements IAction {

	private static final String ENTER_ROOM_NUM = "Enter room num: ";
	private static final String ENTER_ROOM_CAPACITY = "Enter room capacity: ";
	private static final String ENTER_ROOM_STAR = "Enter number of stars (from 1 to 5): ";
	private static final String ENTER_ROOM_STATUS = "Enter room status (1-AVAILABLE,2-OCCUPIED,3-SERVICED): ";
	private static final String ENTER_ROOM_PRICE = "Enter room price: ";
	private static final String ORDER_ADDED = "Order #%s added successfully.";
	private static final String ERROR_CREATING_ORDER = "Error creating order.";
	private static final String ERROR_FIELDS_RANGE = "Entered data is out of specified range.";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(AddRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		try {
			DisplayOperator.printMessage(ENTER_ROOM_NUM);
			int roomNum = Integer.parseInt(scanner.nextLine());

			DisplayOperator.printMessage(ENTER_ROOM_CAPACITY);
			int capacity = Integer.parseInt(scanner.nextLine());

			DisplayOperator.printMessage(ENTER_ROOM_STAR);
			RoomStar star = RoomStar.values()[Integer.parseInt(scanner.nextLine()) - 1];

			DisplayOperator.printMessage(ENTER_ROOM_STATUS);
			RoomStatus status = RoomStatus.values()[Integer.parseInt(scanner.nextLine()) - 1];

			DisplayOperator.printMessage(ENTER_ROOM_PRICE);
			int price = Integer.parseInt(scanner.nextLine());

			Boolean result = Hotel.getInstance().addRoom(roomNum, capacity, star, status, price);
			if (result) {
				DisplayOperator.printMessage(String.format(ORDER_ADDED, roomNum));
			} else {
				DisplayOperator.printMessage(ERROR_CREATING_ORDER);
			}
		} catch (InputMismatchException | NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
			logger.error(e);
		} catch (IndexOutOfBoundsException e) {
			DisplayOperator.printMessage(ERROR_FIELDS_RANGE);
			logger.error(e);
		}
	}

}

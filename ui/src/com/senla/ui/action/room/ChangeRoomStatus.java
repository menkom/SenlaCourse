package com.senla.ui.action.room;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.facade.Hotel;
import com.senla.hotel.property.HotelProperty;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ChangeRoomStatus implements IAction {
	private static final String ENTER_ROOM_NUM = "Enter room num: ";
	private static final String ENTER_ROOM_STATUS = "Enter room new status (1-AVAILABLE,2-OCCUPIED,3-SERVICED): ";
	private static final String ROOM_STATUS_CHANGED = "Room #%s status changed to %s.";
	private static final String ERROR_CHANGING_STATUS = "Error changing room status.";
	private static final String ERROR_FIELDS_TYPE = "Input correct fields type.";
	private static final String ERROR_NO_RIGHTS = "You have no permission to change room status.";

	private static final Logger logger = Logger.getLogger(ChangeRoomStatus.class);

	@Override
	public void execute() {
		if (HotelProperty.getInstance().isAbleChangeRoomStatus()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			try {
				DisplayOperator.printMessage(ENTER_ROOM_NUM);
				int roomNum = Integer.parseInt(scanner.nextLine());

				DisplayOperator.printMessage(ENTER_ROOM_STATUS);
				RoomStatus status = RoomStatus.values()[Integer.parseInt(scanner.nextLine()) - 1];

				Boolean result = Hotel.getInstance().changeRoomStatus(roomNum, status);
				if (result) {
					DisplayOperator.printMessage(String.format(ROOM_STATUS_CHANGED, roomNum, status));
				} else {
					DisplayOperator.printMessage(ERROR_CHANGING_STATUS);
				}

			} catch (InputMismatchException | NumberFormatException e) {
				DisplayOperator.printMessage(ERROR_FIELDS_TYPE);
				logger.error(e);
			}
		} else {
			DisplayOperator.printMessage(ERROR_NO_RIGHTS);
		}
	}

}

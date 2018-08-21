package com.senla.ui.action.room;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ShowRoomInfo implements IAction {

	private static final String ERROR_NEED_ROOM = "You need to enter room number.";
	private static final String ERROR_ROOM_NUM = "Room #%s not found.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(ShowRoomInfo.class);

	@Override
	public void execute() {
		int roomNum = 0;
		DisplayOperator.printMessage("Enter room num: ");
		try {
			roomNum = Input.inputInteger();
			Room room = Hotel.getInstance().getRoomByNum(roomNum);
			if (room == null) {
				DisplayOperator.printMessage(String.format(ERROR_ROOM_NUM, roomNum));
			} else {
				DisplayOperator.printRoomInfo(room);
			}
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_NEED_ROOM);
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}
	}

}

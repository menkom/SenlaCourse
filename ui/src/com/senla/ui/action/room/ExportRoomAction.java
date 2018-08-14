package com.senla.ui.action.room;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.util.DisplayOperator;

public class ExportRoomAction implements IAction {

	private static final String ENTER_ROOM_NUM = "Enter room num to export: ";
	private static final String ENTER_FILE_NAME = "Enter file name to export (room_RoomNum.csv if empty by default): ";
	private static final String ROOM_EXPORTED = "Room #%s exported.";
	private static final String ROOM_EXPORT_FAILED = "Error during room export.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_ROOM_NUM);
		Integer roomNum = Input.inputInteger();

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();

		Boolean result = Hotel.getInstance().exportRoomCSV(roomNum, fileName);
		if (result) {
			DisplayOperator.printMessage(String.format(ROOM_EXPORTED, roomNum));
		} else {
			DisplayOperator.printMessage(ROOM_EXPORT_FAILED);
		}
	}

}

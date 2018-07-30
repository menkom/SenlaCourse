package com.senla.ui.action.room;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowRoomInfo implements IAction {

	private static final Logger logger = Logger.getLogger(ShowRoomInfo.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int roomNum = 0;
		DisplayOperator.printMessage("Enter room num: ");
		try {
			roomNum = scanner.nextInt();
			Room room = Hotel.getInstance().getRoomByNum(roomNum);
			if (room == null) {
				DisplayOperator.printMessage("Room #" + Integer.toString(roomNum) + " not found.");
				logger.error("Null room");
			} else {
				DisplayOperator.printRoomInfo(room);
			}
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("You need to enter room number.");
			logger.error(e.toString());
		}
	}

}

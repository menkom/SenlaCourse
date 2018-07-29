package com.senla.ui.action;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class AddRoom implements IAction {
	private static final Logger logger = Logger.getLogger(AddRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int roomNum = -1;
		int capacity = -1;
		RoomStar star = RoomStar.values()[0];
		RoomStatus status = RoomStatus.values()[0];
		int price = -1;

		try {
			DisplayOperator.printMessage("Enter room number: ");
			roomNum = scanner.nextInt();
			DisplayOperator.printMessage("Enter room capacity: ");
			capacity = scanner.nextInt();

			DisplayOperator.printMessage("Enter number of stars (from 1 to 5): ");
			star = RoomStar.values()[scanner.nextInt() - 1];

			DisplayOperator.printMessage("Enter room status (1-AVAILABLE,2-OCCUPIED,3-SERVICED): ");
			status = RoomStatus.values()[scanner.nextInt() - 1];

			DisplayOperator.printMessage("Enter room price: ");
			price = scanner.nextInt();

			Hotel.getInstance().addRoom(roomNum, capacity, star, status, price);
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("Input correct field types.");
			logger.error(e);
		} catch (IndexOutOfBoundsException e) {
			DisplayOperator.printMessage("Keep data in specified range.");
			logger.error(e);
		}
	}

}

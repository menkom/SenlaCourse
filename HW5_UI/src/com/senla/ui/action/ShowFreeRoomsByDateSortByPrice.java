package com.senla.ui.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowFreeRoomsByDateSortByPrice implements IAction {

	private static final Logger logger = Logger.getLogger(ShowFreeRoomsByDateSortByPrice.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter date (format dd/MM/yyyy): ");
		String dateInString = scanner.next();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date = formatter.parse(dateInString);

			List<Room> rooms = Hotel.getInstance().getFreeRoomsByDateSortByCapacity(date);
			if (rooms.size() > 0) {
				DisplayOperator.printRooms(rooms);
			} else {
				DisplayOperator.printMessage("No rooms found.");
			}
		} catch (ParseException e) {
			DisplayOperator.printMessage("Date format error.");
			logger.error(e);
		}
	}

}

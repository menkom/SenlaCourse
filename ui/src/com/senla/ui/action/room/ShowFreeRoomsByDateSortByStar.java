package com.senla.ui.action.room;

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

public class ShowFreeRoomsByDateSortByStar implements IAction {

	private static final String ENTER_DATE = "Enter date (format dd/MM/yyyy): ";
	private static final String NO_ROOMS = "No rooms found.";
	private static final String ERROR_ROOMS_SEARCH = "Error during rooms search.";
	private static final String ERROR_DATE_FORMAT = "Date format error.";

	private static final Logger logger = Logger.getLogger(ShowFreeRoomsByDateSortByStar.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		DisplayOperator.printMessage(ENTER_DATE);
		String dateInString = scanner.nextLine();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date = formatter.parse(dateInString);

			List<Room> rooms = Hotel.getInstance().getFreeRoomsByDateSortByStar(date);
			if (rooms == null) {
				DisplayOperator.printMessage(ERROR_ROOMS_SEARCH);
			} else {
				if (rooms.size() > 0) {
					DisplayOperator.printRooms(rooms);
				} else {
					DisplayOperator.printMessage(NO_ROOMS);
				}
			}

		} catch (ParseException e) {
			DisplayOperator.printMessage(ERROR_DATE_FORMAT);
			logger.error(e);
		}
	}

}

package com.senla.ui.action.room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class OrderRoom implements IAction {

	private static final String ENTER_ORDER_NUM = "Enter order number: ";
	private static final String ENTER_NAME = "Enter client name: ";
	private static final String ENTER_ROOM_NUM = "Enter room number: ";
	private static final String ENTER_DATE_START = "Enter start date (format dd/MM/yyyy) (current date if empty): ";
	private static final String ENTER_DATE_FINISH = "Enter finish date (format dd/MM/yyyy) (no date if empty): ";
	private static final String ERROR_ROOM_NUM_OR_CLIENT = "Room or Client not found.";
	private static final String ERROR_WRONG_INPUT = "Wrong input data.";
	private static final String ERROR_WRONG_DATE = "Wrong date format.";
	private static final String ERROR_ORDERING = "Error ordering room.";
	private static final String ROOM_ORDERED = "Room %s ordered by %s.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(OrderRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Integer orderNum = 0;
		Integer roomNum = 0;
		String clientName = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			DisplayOperator.printMessage(ENTER_ORDER_NUM);
			orderNum = Integer.parseInt(scanner.nextLine());
			DisplayOperator.printMessage(ENTER_NAME);
			clientName = scanner.nextLine();
			DisplayOperator.printMessage(ENTER_ROOM_NUM);
			roomNum = Integer.parseInt(scanner.nextLine());
			DisplayOperator.printMessage(ENTER_DATE_START);
			String dateStartInString = scanner.nextLine();
			DisplayOperator.printMessage(ENTER_DATE_FINISH);
			String dateFinishInString = scanner.nextLine();

			Date dateStart = new Date();
			Date dateFinish = null;

			if (!dateStartInString.equals("")) {
				dateStart = formatter.parse(dateStartInString);
			}

			if (!dateFinishInString.equals("")) {
				dateFinish = formatter.parse(dateFinishInString);
			}

			Boolean result = Hotel.getInstance().orderRoom(orderNum, clientName, roomNum, dateStart, dateFinish);

			if (result) {
				DisplayOperator.printMessage(String.format(ROOM_ORDERED, roomNum, clientName));
			} else {
				DisplayOperator.printMessage(ERROR_ORDERING);
			}

		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_WRONG_INPUT);
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage(ERROR_ROOM_NUM_OR_CLIENT);
			logger.error(e.toString());
		} catch (ParseException e) {
			DisplayOperator.printMessage(ERROR_WRONG_DATE);
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		}
	}

}

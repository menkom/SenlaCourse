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

	private static final Logger logger = Logger.getLogger(OrderRoom.class);

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int roomNum = 0;
		String clientName = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			DisplayOperator.printMessage("Enter client name: ");
			clientName = scanner.nextLine();
			DisplayOperator.printMessage("Enter room number: ");
			roomNum = Integer.parseInt(scanner.nextLine());
			DisplayOperator.printMessage("Enter start date (format dd/MM/yyyy) (current date if empty): ");
			String dateStartInString = scanner.nextLine();
			DisplayOperator.printMessage("Enter finish date (format dd/MM/yyyy) (no date if empty): ");
			String dateFinishInString = scanner.nextLine();

			Date dateStart = new Date();
			Date dateFinish = null;

			if (!dateStartInString.equals("")) {
				dateStart = formatter.parse(dateStartInString);
			}

			if (!dateFinishInString.equals("")) {
				dateFinish = formatter.parse(dateFinishInString);
			}

			DisplayOperator.printOrderInfo(Hotel.getInstance().orderRoom(clientName, roomNum, dateStart, dateFinish));
		} catch (InputMismatchException e) {
			DisplayOperator.printMessage("Wrong input data.");
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage("Order #" + Integer.toString(roomNum) + " not found.");
			logger.error(e.toString());
		} catch (ParseException e) {
			DisplayOperator.printMessage("Wrong date format.");
			logger.error(e.toString());
		}
	}

}

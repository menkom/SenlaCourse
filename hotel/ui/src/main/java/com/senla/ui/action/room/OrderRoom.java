package com.senla.ui.action.room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.ui.util.DisplayOperator;
import com.senla.ui.util.Input;

public class OrderRoom implements IAction {

	private static final String ENTER_ORDER_NUM = "Enter order number: ";
	private static final String ENTER_CLIENT_ID = "Enter client Id: ";
	private static final String ENTER_ROOM_ID = "Enter room Id: ";
	private static final String ENTER_DATE_START = "Enter start date (format dd/MM/yyyy) (current date if empty): ";
	private static final String ENTER_DATE_FINISH = "Enter finish date (format dd/MM/yyyy) (no date if empty): ";
	private static final String ERROR_ROOM_NUM_OR_CLIENT = "Room or Client not found.";
	private static final String ERROR_WRONG_INPUT = "Wrong input data.";
	private static final String ERROR_WRONG_DATE = "Wrong date format.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";

	private static final Logger logger = Logger.getLogger(OrderRoom.class);

	@Override
	public void execute() {
		Integer orderNum = 0;
		Integer roomId = null;
		Integer clientId = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			DisplayOperator.printMessage(ENTER_ORDER_NUM);
			orderNum = Input.inputInteger();
			DisplayOperator.printMessage(ENTER_CLIENT_ID);
			clientId = Input.inputInteger();
			Client client = Hotel.getInstance().getClientById(clientId);
			DisplayOperator.printMessage(ENTER_ROOM_ID);
			roomId = Input.inputInteger();
			Room room = Hotel.getInstance().getRoomById(roomId);

			DisplayOperator.printMessage(ENTER_DATE_START);
			String dateStartInString = Input.inputString();
			DisplayOperator.printMessage(ENTER_DATE_FINISH);
			String dateFinishInString = Input.inputString();

			Date dateStart = new Date();
			Date dateFinish = null;

			if (!dateStartInString.equals("")) {
				dateStart = formatter.parse(dateStartInString);
			}

			if (!dateFinishInString.equals("")) {
				dateFinish = formatter.parse(dateFinishInString);
			}

			Order order = new Order(orderNum, client, room, dateStart, dateFinish);

			Hotel.getInstance().addOrder(order);

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

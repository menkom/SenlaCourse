package com.senla.ui.action.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.ui.base.IAction;
import com.senla.ui.util.DisplayOperator;
import com.senla.ui.util.Input;

public class CloneOrder implements IAction {
	private static final String ENTER_ORDER_ID = "Enter order id: ";
	private static final String ENTER_ORDER_NUM = "Enter new order num: ";
	private static final String ERROR_NEED_ORDER = "You need to enter order id.";
	private static final String ERROR_CLONING = "Error during clonning.";
	private static final String ORDER_CLONED = "Order with Id #%s cloned.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";
	private static final String WANT_CHANGE = "Do you want to change anything?[yes or no]";
	private static final String WANT_SAVE = "Do you want to save?[yes or no]";

	private static final String ENTER_CLIENT_ID = "Enter client Id: ";
	private static final String ENTER_ROOM_ID = "Enter room Id: ";
	private static final String ENTER_DATE_START = "Enter start date (format dd/MM/yyyy): ";
	private static final String ENTER_DATE_FINISH = "Enter finish date (format dd/MM/yyyy): ";
	private static final String ERROR_ROOM_NUM_OR_CLIENT = "Room or Client not found.";
	private static final String ERROR_WRONG_DATE = "Wrong date format.";

	private static final String LEAVE_EMPTY_NO_CHANGE = "Leave field empty if it need no changes.";
	private static final Logger logger = Logger.getLogger(CloneOrder.class);

	@Override
	public void execute() {
		int orderId = 0;
		DisplayOperator.printMessage(ENTER_ORDER_ID);
		try {
			orderId = Input.inputInteger();

			Order order = Hotel.getInstance().getOrderById(orderId);

			Order clone = Hotel.getInstance().cloneOrder(order);

			if (clone == null) {
				DisplayOperator.printMessage(ERROR_CLONING);
			} else {
				DisplayOperator.printMessage(String.format(ORDER_CLONED, orderId));

				DisplayOperator.printOrderInfo(Hotel.getInstance().getOrderById(orderId));

				DisplayOperator.printOrderInfo(clone);

				DisplayOperator.printMessage(WANT_CHANGE);

				String change = Input.inputString();
				if (change.equalsIgnoreCase("y") || change.equalsIgnoreCase("yes")) {

					DisplayOperator.printMessage(LEAVE_EMPTY_NO_CHANGE);
					DisplayOperator.printMessage(ENTER_ORDER_NUM);
					String newOrderNum = Input.inputString();
					if (!newOrderNum.equals("")) {
						clone.setNum(Integer.parseInt(newOrderNum));
					}

					DisplayOperator.printMessage(ENTER_CLIENT_ID);
					int clientId = Input.inputInteger();
					clone.setClient(Hotel.getInstance().getClientById(clientId));

					DisplayOperator.printMessage(ENTER_ROOM_ID);
					int roomId = Input.inputInteger();
					clone.setRoom(Hotel.getInstance().getRoomById(roomId));

					DisplayOperator.printMessage(ENTER_DATE_START);
					String dateStartInString = Input.inputString();

					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					if (!dateStartInString.equals("")) {
						clone.setStartDate(formatter.parse(dateStartInString));
					}

					DisplayOperator.printMessage(ENTER_DATE_FINISH);
					String dateFinishInString = Input.inputString();

					if (!dateFinishInString.equals("")) {
						clone.setFinishDate(formatter.parse(dateFinishInString));
					}

				}

				DisplayOperator.printMessage(WANT_SAVE);

				String save = Input.inputString();
				if (save.equalsIgnoreCase("y") || save.equalsIgnoreCase("yes")) {
					Hotel.getInstance().addOrder(clone);
				}
			}

		} catch (InputMismatchException e) {
			DisplayOperator.printMessage(ERROR_NEED_ORDER);
			logger.error(e.toString());
		} catch (NullPointerException e) {
			DisplayOperator.printMessage(ERROR_ROOM_NUM_OR_CLIENT);
			logger.error(e.toString());
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(ERROR_IN_FIELDS);
			logger.error(e);
		} catch (ParseException e) {
			DisplayOperator.printMessage(ERROR_WRONG_DATE);
			logger.error(e);
		}

	}

}

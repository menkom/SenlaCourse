package com.senla.ui.action.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Order;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class CloneOrder implements IAction {
	private static final String ENTER_ORDER_NUM = "Enter order num: ";
	private static final String ERROR_NEED_ORDER = "You need to enter order number.";
	private static final String ERROR_CLONING = "Error during clonning.";
	private static final String ORDER_CLONED = "Order #%s cloned.";
	private static final String ERROR_IN_FIELDS = "Input correct fields type.";
	private static final String WANT_CHANGE = "Do you want to change anything?[yes or no]";
	private static final String WANT_SAVE = "Do you want to save?[yes or no]";
	private static final String ORDER_SAVED = "Order saved and recieved id #%s.";

	private static final String ENTER_NAME = "Enter client name: ";
	private static final String ENTER_ROOM_NUM = "Enter room number: ";
	private static final String ENTER_DATE_START = "Enter start date (format dd/MM/yyyy): ";
	private static final String ENTER_DATE_FINISH = "Enter finish date (format dd/MM/yyyy): ";
	private static final String ERROR_ROOM_NUM_OR_CLIENT = "Room or Client not found.";
	private static final String ERROR_WRONG_DATE = "Wrong date format.";
	private static final String ERROR_SAVING = "Error saving order.";

	private static final String LEAVE_EMPTY_NO_CHANGE = "Leave field empty if it need no changes.";
	private static final Logger logger = Logger.getLogger(CloneOrder.class);

	@Override
	public void execute() {
		int orderNum = 0;
		DisplayOperator.printMessage(ENTER_ORDER_NUM);
		try {
			orderNum = Input.inputInteger();

			Order clone = Hotel.getInstance().cloneOrder(orderNum);

			if (clone == null) {
				DisplayOperator.printMessage(ERROR_CLONING);
			} else {
				DisplayOperator.printMessage(String.format(ORDER_CLONED, orderNum));

				DisplayOperator.printOrderInfo(Hotel.getInstance().getOrderByNum(orderNum));

				DisplayOperator.printOrderInfo(clone);

				DisplayOperator.printMessage(WANT_CHANGE);

				String change = Input.inputString();
				if (change.toLowerCase().equals("y") || change.toLowerCase().equals("yes")) {

					DisplayOperator.printMessage(LEAVE_EMPTY_NO_CHANGE);
					DisplayOperator.printMessage(ENTER_ORDER_NUM);
					String newOrderNum = Input.inputString();
					if (!newOrderNum.equals("")) {
						clone.setNum(Integer.parseInt(newOrderNum));
					}

					DisplayOperator.printMessage(ENTER_NAME);
					String clientName = Input.inputString();
					if (!clientName.equals("")) {
						clone.setClient(Hotel.getInstance().getClientByName(clientName));
					}

					DisplayOperator.printMessage(ENTER_ROOM_NUM);
					String roomNum = Input.inputString();
					if (!roomNum.equals("")) {
						clone.setRoom(Hotel.getInstance().getRoomByNum(Integer.parseInt(roomNum)));
					}

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
				if (save.toLowerCase().equals("y") || save.toLowerCase().equals("yes")) {
					if (Hotel.getInstance().addOrder(clone)) {
						DisplayOperator.printMessage(String.format(ORDER_SAVED, clone.getId()));
					} else {
						DisplayOperator.printMessage(ERROR_SAVING);
					}
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

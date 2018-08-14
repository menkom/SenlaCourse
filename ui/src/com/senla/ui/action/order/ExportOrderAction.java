package com.senla.ui.action.order;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.util.DisplayOperator;

public class ExportOrderAction implements IAction {

	private static final String ENTER_ORDER_NUM = "Enter order num to export: ";
	private static final String ENTER_FILE_NAME = "Enter file name to export (order_OrderNum.csv if empty by default): ";
	private static final String ORDER_EXPORTED = "Order #%s exported.";
	private static final String ORDER_EXPORT_FAILED = "Error during order export.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_ORDER_NUM);
		Integer orderNum = Input.inputInteger();

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();

		Boolean result = Hotel.getInstance().exportOrderCSV(orderNum, fileName);
		if (result) {
			DisplayOperator.printMessage(String.format(ORDER_EXPORTED, orderNum));
		} else {
			DisplayOperator.printMessage(ORDER_EXPORT_FAILED);
		}
	}

}

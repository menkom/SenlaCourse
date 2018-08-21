package com.senla.ui.action.client;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.FileCheck;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ImportClientAction implements IAction {

	private static final String ENTER_FILE_NAME = "Enter file name to import clients: ";
	private static final String CLIENT_IMPORT = "File %s imported.";
	private static final String CLIENT_IMPORT_FAILED = "Error during client import.";
	private static final String FILE_NOT_EXIST = "File %s not exist.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();
		if (FileCheck.isFileExist(fileName)) {
			Boolean result = Hotel.getInstance().importClientsCSV(fileName);
			if (result) {
				DisplayOperator.printMessage(String.format(CLIENT_IMPORT, fileName));
			} else {
				DisplayOperator.printMessage(CLIENT_IMPORT_FAILED);
			}
		} else {
			DisplayOperator.printMessage(String.format(FILE_NOT_EXIST, fileName));
		}
	}

}

package com.senla.ui.action.service;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.FileCheck;
import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class ImportServiceAction implements IAction {

	private static final String ENTER_FILE_NAME = "Enter file name to import services: ";
	private static final String FILE_IMPORT = "File %s imported.";
	private static final String FILE_IMPORT_FAILED = "Error during services import.";
	private static final String FILE_NOT_EXIST = "File %s not exist.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();
		if (FileCheck.isFileExist(fileName)) {
			Boolean result = Hotel.getInstance().importServicesCSV(fileName);
			if (result) {
				DisplayOperator.printMessage(String.format(FILE_IMPORT, fileName));
			} else {
				DisplayOperator.printMessage(FILE_IMPORT_FAILED);
			}
		} else {
			DisplayOperator.printMessage(String.format(FILE_NOT_EXIST, fileName));
		}
	}
}

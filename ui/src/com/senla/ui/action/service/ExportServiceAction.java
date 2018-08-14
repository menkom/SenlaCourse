package com.senla.ui.action.service;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.util.DisplayOperator;

public class ExportServiceAction implements IAction {

	private static final String ENTER_SERVICE_CODE = "Enter service code to export: ";
	private static final String ENTER_FILE_NAME = "Enter file name to export (service_ServiceCode.csv if empty by default): ";
	private static final String SERVICE_EXPORTED = "Service with code %s exported.";
	private static final String SERVICE_EXPORT_FAILED = "Error during service export.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_SERVICE_CODE);
		Integer serviceCode = Input.inputInteger();

		DisplayOperator.printMessage(ENTER_FILE_NAME);
		String fileName = Input.inputString();

		Boolean result = Hotel.getInstance().exportServiceCSV(serviceCode, fileName);
		if (result) {
			DisplayOperator.printMessage(String.format(SERVICE_EXPORTED, serviceCode));
		} else {
			DisplayOperator.printMessage(SERVICE_EXPORT_FAILED);
		}
	}
}

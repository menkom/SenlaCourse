package com.senla.ui.action.service;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.ui.util.Input;
import com.senla.util.DisplayOperator;

public class ExportServiceAction implements IAction {

	private static final String ENTER_SERVICE_CODE = "Enter service code to export: ";
	private static final String SERVICE_EXPORTED = "Service with code %s exported.";
	private static final String SERVICE_EXPORT_FAILED = "Error during service export.";

	@Override
	public void execute() {

		DisplayOperator.printMessage(ENTER_SERVICE_CODE);
		Integer serviceCode = Input.inputInteger();

		Boolean result = Hotel.getInstance().exportServiceCSV(serviceCode);
		if (result) {
			DisplayOperator.printMessage(String.format(SERVICE_EXPORTED, serviceCode));
		} else {
			DisplayOperator.printMessage(SERVICE_EXPORT_FAILED);
		}
	}
}

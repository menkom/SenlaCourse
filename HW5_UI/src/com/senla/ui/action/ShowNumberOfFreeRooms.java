package com.senla.ui.action;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowNumberOfFreeRooms implements IAction {

	@Override
	public void execute() {
		DisplayOperator.printMessage("Number of free rooms : " + Hotel.getInstance().getNumberOfFreeRooms());
	}

}

package com.senla.ui.action;

import com.senla.ui.base.IAction;
import com.senla.ui.base.MenuController;
import com.senla.ui.util.DisplayOperator;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		MenuController.setInProgress(false);
		DisplayOperator.printMessage("Thank you for using application \"Hotel\". Bye!");
	}

}

package com.senla.ui.action;

import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		DisplayOperator.printMessage("Thank you for using application \"Hotel\". Bye!");
		System.exit(0);
	}

}

package com.senla.exec;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;

//import com.senla.datahelp.DataFiller;
import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.MenuController;
import com.senla.util.DisplayOperator;

public class Executive {

	private static final Logger logger = Logger.getLogger(Executive.class);

	public static void main(String[] args) {

		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		logger.info("Path to DB: " + dbPath);

		try {
			Hotel.getInstance().load(dbPath);
		} catch (NumberFormatException e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		} catch (IOException e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		} catch (ParseException e) {
			DisplayOperator.printMessage(e.toString());
			logger.error(e);
		}

		// new DataFiller().runDataIO();

		MenuController menuController = new MenuController();
		menuController.run();

		// Hotel.getInstance().save(dbPath);

	}

}

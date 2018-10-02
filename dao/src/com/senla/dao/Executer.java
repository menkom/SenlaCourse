package com.senla.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Executer {

	private static final Logger logger = Logger.getLogger(Executer.class);

	private static final String query = "select * from client";

	public static void main(String[] args) {
		try {
			ResultSet result = DaoHandler.getInstance().executeQuery(query);

			while (result.next()) {
				String userid = result.getString("id");
				String username = result.getString("name");

				System.out.print("user_id: " + userid + "; ");
				System.out.println("user_name: " + username);
			}

		} catch (SQLException e) {
			System.out.println(e);
			logger.error(e);

		} finally {
			DaoHandler.getInstance().closeConnection();
		}
	}
}

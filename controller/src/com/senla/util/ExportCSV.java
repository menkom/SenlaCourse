package com.senla.util;

import java.io.FileWriter;
import java.io.IOException;

import com.senla.hotel.model.Client;
import com.senla.hotel.model.Room;

public class ExportCSV {

	public static Boolean saveClientCSV(Client client, String path) throws IOException {
		Boolean result = false;
		try (FileWriter fileWriter = new FileWriter(path)) {
			fileWriter.append(client.toString());
		}
		result = true;
		return result;
	}

	public static Boolean saveRoomCSV(Room room, String path) throws IOException {
		Boolean result = false;
		try (FileWriter fileWriter = new FileWriter(path)) {
			fileWriter.append(room.toString());
		}
		result = true;
		return result;
	}

	public static <T> Boolean saveCSV(T object, String path) throws IOException {
		Boolean result = false;
		try (FileWriter fileWriter = new FileWriter(path)) {
			fileWriter.append(object.toString());
		}
		result = true;
		return result;
	}

}

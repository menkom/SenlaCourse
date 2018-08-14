package com.senla.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.senla.converter.ListConverter;
import com.senla.exception.NoEntryException;
import com.senla.hotel.model.Client;

public class ExportCSV {

	public static Boolean saveCSV(String line, String path) throws IOException {
		Boolean result = false;
		try (FileWriter fileWriter = new FileWriter(path)) {
			fileWriter.append(line);
		}
		result = true;
		return result;
	}

	private static List<String> loadCSV(String path) throws IOException {
		List<String> result = new ArrayList<>();
		String line;
		try (FileReader fileReader = new FileReader(path)) {
			try (BufferedReader br = new BufferedReader(fileReader);) {
				while ((line = br.readLine()) != null) {
					result.add(line);
				}
			}
		}
		return result;
	}

	public static List<Client> getClientsFromCSV(String file) throws NoEntryException, IOException {
		List<Client> result = new ArrayList<>();
		List<String> list = loadCSV(file);
		if (list != null) {
			result = ListConverter.getClients(list);
		}
		return result;
	}

}

package com.senla.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.senla.base.BaseObject;

public class ExportCSV {

	public static <T extends BaseObject> Boolean saveCSV(T object, String path) throws IOException {
		Boolean result = false;

		try (FileWriter fileWriter = new FileWriter(path)) {
			fileWriter.append(object.toString());
		}
		result = true;
		return result;
	}

	public static <T extends BaseObject> List<T> loadCSV(String path) throws IOException {
		List<T> result = new ArrayList<T>();
		String line;
		try (FileReader fileReader = new FileReader(path)) {
			try (BufferedReader br = new BufferedReader(fileReader);) {
				line = br.readLine();
			}
		}
		return result;
	}
}

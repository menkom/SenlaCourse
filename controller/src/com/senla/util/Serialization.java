package com.senla.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String filePath) throws IOException, ClassNotFoundException {
		T result = null;
		try (ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(filePath))) {
			result = (T) objectStream.readObject();
			objectStream.close();
		}
		return result;
	}

	public static Boolean serialize(Object object, String filePath) throws IOException {
		Boolean result = false;
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));) {
			outputStream.writeObject(object);
			outputStream.flush();
			result = true;
		}
		return result;
	}

}

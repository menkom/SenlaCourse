package com.senla.annotation.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;

public class CsvImport {

	public static List<String> loadCsvFile(String path) throws IOException {
		List<String> result = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			String line;
			try (FileReader fileReader = new FileReader(file)) {
				try (BufferedReader br = new BufferedReader(fileReader);) {
					while ((line = br.readLine()) != null) {
						result.add(line);
					}
				}
			}
		}
		return result;
	}

	public static boolean setCsvProperties(Object item, Class<?> itemClass, String[] fieldValues)
			throws IllegalArgumentException, IllegalAccessException, ParseException {
		boolean result = false;
		if (!itemClass.getSuperclass().equals(Object.class)) {
			setCsvProperties(item, itemClass.getSuperclass(), fieldValues);
		}

		final Field[] fields = itemClass.getDeclaredFields();

		for (Field field : fields) {

			CsvProperty fieldCsvProperty = field.getAnnotation(CsvProperty.class);

			if (fieldCsvProperty != null) {
				if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {

					Integer columnNumber = fieldCsvProperty.columnNumber();
					setSimpleProperty(item, field, fieldValues[columnNumber]);

				} else if (fieldCsvProperty.propertyType() == PropertyType.CompositeProperty) {
//					Integer columnNumber = Integer.valueOf(fieldCsvProperty.columnNumber());
//					String keyFieldName = fieldCsvProperty.keyField();
//					result.putAll(getCompositeProperty(item, field, columnNumber, keyFieldName, separator));
				}
			}
		}
		result = true;
		return result;
	}

	private static boolean setSimpleProperty(Object item, Field field, String fieldValue)
			throws IllegalArgumentException, IllegalAccessException, ParseException {
		return setValue(item, field, fieldValue);
	}

	private static boolean setValue(Object item, Field field, String fieldValue)
			throws IllegalArgumentException, IllegalAccessException, ParseException {
		boolean result = false;
		boolean isAccessible = field.isAccessible();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		if (fieldValue.equals("null")) {
			field.set(item, null);
		} else if (field.getType().equals(String.class)) {
			field.set(item, fieldValue);
		} else if (field.getType().equals(Integer.class)) {
			field.set(item, Integer.parseInt(fieldValue));
		} else if (field.getType().equals(Date.class)) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			field.set(item, formatter.parse(fieldValue));
		}

		field.setAccessible(isAccessible);
		result = true;
		return result;
	}

}

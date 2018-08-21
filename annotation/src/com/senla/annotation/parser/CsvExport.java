package com.senla.annotation.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;

public class CsvExport {

	private static final String EOL = "\n";
	private static final String LIST_FIELD_SEPARATOR = ",";

	private static SortedMap<Integer, String> getSimpleProperty(Object item, Field field, Integer columnNumber)
			throws IllegalArgumentException, IllegalAccessException {
		SortedMap<Integer, String> result = new TreeMap<Integer, String>();
		String itemValue = getValue(item, field);
		result.put(columnNumber, itemValue);
		return result;
	}

	private static SortedMap<Integer, String> getCompositeProperty(Object item, Field field, Integer columnNumber,
			String keyFieldName)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

		SortedMap<Integer, String> result = new TreeMap<Integer, String>();

		boolean isAccessible = field.isAccessible();

		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		if (field.get(item) != null) {
			Class<? extends Object> fieldClass = field.get(item).getClass();

			if (field.getType().equals(List.class)) {
				List<?> list = (List<?>) field.get(item);
				if (list.size() > 0) {
					StringBuilder itemValue = new StringBuilder();

					for (Object element : list) {
						itemValue.append(getKeyValue(element, element.getClass(), keyFieldName))
								.append(LIST_FIELD_SEPARATOR);
					}
					result.put(columnNumber, itemValue.toString());
				} else {
					result.put(columnNumber, "null");
				}
			} else {
				String itemValue = getKeyValue(field.get(item), fieldClass, keyFieldName);

				result.put(columnNumber, itemValue);
			}
			field.setAccessible(isAccessible);
		} else {
			result.put(columnNumber, "null");
		}
		return result;
	}

	private static String getValue(Object item, Field field) throws IllegalArgumentException, IllegalAccessException {
		String result = null;
		boolean isAccessible = field.isAccessible();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		if (field.get(item) == null) {
			result = "null";
		} else if (field.getType().equals(Date.class)) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			result = formatter.format((Date) field.get(item));
		} else {
			result = field.get(item).toString();
		}
		field.setAccessible(isAccessible);
		return result;
	}

	private static String getKeyValue(Object item, Class<?> itemClass, String keyFieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String result = null;
		if (!keyFieldName.equals("")) {
			if (hasField(item, itemClass, keyFieldName)) {
				Field keyField = itemClass.getDeclaredField(keyFieldName);
				result = getValue(item, keyField);
			} else {
				result = getKeyValue(item, itemClass.getSuperclass(), keyFieldName);
			}
		}
		return result;
	}

	private static boolean hasField(Object item, Class<?> itemClass, String keyFieldName) {
		Field[] fields = itemClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(keyFieldName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean saveCsvFile(List<String> lines, String fileName) throws IOException {
		Boolean result = false;
		File file = new File(fileName);
		if (!file.getName().equals("")) {
			try (FileWriter fileWriter = new FileWriter(file)) {
				try (BufferedWriter br = new BufferedWriter(fileWriter);) {
					for (String line : lines) {
						br.write(line);
					}
				}
			}
			result = true;
		}
		return result;
	}

	public static SortedMap<Integer, String> getCsvProperties(Object item, Class<?> itemClass, String separator)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SortedMap<Integer, String> result = new TreeMap<Integer, String>();

		if (!itemClass.getSuperclass().equals(Object.class)) {
			result.putAll(getCsvProperties(item, itemClass.getSuperclass(), separator));
		}

		final Field[] fields = itemClass.getDeclaredFields();

		for (Field field : fields) {

			CsvProperty fieldCsvProperty = field.getAnnotation(CsvProperty.class);

			if (fieldCsvProperty != null) {
				if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {

					Integer columnNumber = fieldCsvProperty.columnNumber();
					result.putAll(getSimpleProperty(item, field, columnNumber));
				} else if (fieldCsvProperty.propertyType() == PropertyType.CompositeProperty) {
					Integer columnNumber = Integer.valueOf(fieldCsvProperty.columnNumber());
					String keyFieldName = fieldCsvProperty.keyField();

					result.putAll(getCompositeProperty(item, field, columnNumber, keyFieldName));
				}
			}
		}
		return result;
	}

	public static String mapToString(SortedMap<Integer, String> map, String separator) {
		StringBuilder result = new StringBuilder();
		for (SortedMap.Entry<Integer, String> entry : map.entrySet()) {
			result.append(entry.getValue()).append(separator);
		}
		result.append(EOL);
		return result.toString();
	}

}

package com.senla.annotation.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;

public class CsvImport {

	private static final String LIST_FIELD_SEPARATOR = ",";

	private static boolean setSimpleProperty(Object item, Field field, String fieldValue)
			throws IllegalArgumentException, IllegalAccessException, ParseException {
		return setValue(item, field, fieldValue);
	}

	private static boolean setCompositeProperty(Object item, Field field, String fieldValue, String keyFieldName)
			throws IllegalArgumentException, IllegalAccessException, ParseException {
		boolean result = false;
//		System.out.println("fieldType:" + field.getType());
//		System.out.println("fieldValue:" + fieldValue);
		boolean isAccessible = field.isAccessible();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		if (!fieldValue.equals("null")) {
			if (field.getType() == List.class) {
				String[] keyValues = fieldValue.split(LIST_FIELD_SEPARATOR);
//				System.out.println("List:" + Arrays.toString(keyValues));
				try {
					ParameterizedType type = (ParameterizedType) field.getGenericType();
//					System.out.println("type:" + type);
					Class<?> parameter = (Class<?>) type.getActualTypeArguments()[0];
//					System.out.println("parameter:" + parameter);
					for (String keyValue : keyValues) {
						setKeyValue(parameter, keyFieldName, keyValue);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
//				System.out.println("Else:");
				try {
					setKeyValue(field.getType(), keyFieldName, fieldValue);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		field.setAccessible(isAccessible);

		result = true;
		return result;
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

	private static <T> Class<T> setKeyValue(Class<T> itemClass, String keyField, String keyValue) {
		Class<T> result = null;

		System.out.println("itemClass:" + itemClass + "| keyField:" + keyField + "| keyValue:" + keyValue);

		return result;
	}

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
				Integer columnNumber = fieldCsvProperty.columnNumber();
				if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {
					setSimpleProperty(item, field, fieldValues[columnNumber]);
				} else if (fieldCsvProperty.propertyType() == PropertyType.CompositeProperty) {
					String keyFieldName = fieldCsvProperty.keyField();
					setCompositeProperty(item, field, fieldValues[columnNumber], keyFieldName);
				}
			}
		}
		result = true;
		return result;
	}

}

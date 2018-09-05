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
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;

public class CsvImport {

	private static final Logger logger = Logger.getLogger(CsvImport.class);

	private static final String LIST_FIELD_SEPARATOR = ",";

	private static boolean setSimpleProperty(Object item, Field field, String fieldValue) {
		return setValue(item, field, fieldValue);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean setCompositeProperty(Object item, Field field, String fieldValue, String keyFieldName,
			String csvFilePath) throws IOException {
		boolean result = false;
		boolean isAccessible = field.isAccessible();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		if (!fieldValue.equals("null")) {
			try {
				if (field.getType() == List.class) {
					String[] keyValues = fieldValue.split(LIST_FIELD_SEPARATOR);
					Class<?> parameter = getGenericClass(field);

					for (String keyValue : keyValues) {
						result = ((List) field.get(item))
								.add(getCompositePropertyItem(parameter, keyFieldName, keyValue, csvFilePath));

						if (!result) {
							break;
						}
					}
				} else {
					field.set(item, getCompositePropertyItem(field.getType(), keyFieldName, fieldValue, csvFilePath));
					result = true;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error(e);
			}
		} else {
			result = true;
		}
		field.setAccessible(isAccessible);
		return result;
	}

	private static Class<?> getGenericClass(Field field) {
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		return (Class<?>) type.getActualTypeArguments()[0];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean setValue(Object item, Field field, String fieldValue) {
		boolean result = false;
		boolean isAccessible = field.isAccessible();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			if (fieldValue.equals("null")) {
				field.set(item, null);
			} else if (field.getType().equals(String.class)) {
				field.set(item, fieldValue);
			} else if (field.getType().equals(Integer.class)) {
				field.set(item, Integer.parseInt(fieldValue));
			} else if (field.getType().equals(Date.class)) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				field.set(item, formatter.parse(fieldValue));
			} else if (field.getType().isEnum()) {
				Class<? extends Enum> en = (Class<? extends Enum>) field.getType();
				field.set(item, Enum.valueOf(en, fieldValue));
			}
			result = true;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e);
		} catch (ParseException e) {
			logger.error(e);
		}
		field.setAccessible(isAccessible);
		return result;
	}

	private static <T> T getCompositePropertyItem(Class<T> itemClass, String keyField, String keyValue,
			String csvFilePath)  throws IOException{
		T result = null;
		List<T> list = getFromCsv(itemClass, csvFilePath);

		if (list.size() > 0) {
			list.get(0).getClass();
			Field itemKeyField = null;
			itemKeyField = getField(itemClass, keyField);
			if (itemKeyField != null) {
				for (T element : list) {
					boolean isAccessible = itemKeyField.isAccessible();
					if (!itemKeyField.isAccessible()) {
						itemKeyField.setAccessible(true);
					}
					try {
						if (itemKeyField.get(element).toString().equals(keyValue)) {
							result = element;
							break;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						logger.error(e);
					}
					itemKeyField.setAccessible(isAccessible);
				}
			}
		}
		return result;
	}

	private static Field getField(Class<?> itemClass, String fieldName) {
		Field result = null;

		if (hasField(itemClass, fieldName)) {
			try {
				result = itemClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException | SecurityException e) {
				logger.error(e);
			}
		} else if (!itemClass.getSuperclass().equals(Object.class)) {
			result = getField(itemClass.getSuperclass(), fieldName);
		}
		return result;
	}

	private static boolean hasField(Class<?> itemClass, String fieldName) {
		Field[] fields = itemClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public static <T extends Object> List<T> getFromCsv(Class<T> itemClass, String csvFilePath) throws IOException {
		List<T> result = new ArrayList<>();
		if (itemClass != null) {
			CsvEntity itemCsvEntity = itemClass.getAnnotation(CsvEntity.class);
			if (itemCsvEntity != null) {
				String valueSeparator = itemCsvEntity.valueSeparator();
				String filename = itemCsvEntity.filename();
				List<String> csvList;
				try {
					csvList = CsvImport.loadCsvFile(csvFilePath + filename);
					for (String line : csvList) {
						T item = itemClass.newInstance();
						String[] array = line.split(valueSeparator);

						if (CsvImport.setCsvProperties(item, itemClass, array, csvFilePath)) {
							result.add(item);
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					logger.error(e);
				} catch (InstantiationException e) {
					logger.error(e);
				}
			}
		}
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

	public static boolean setCsvProperties(Object item, Class<?> itemClass, String[] fieldValues, String csvFilePath)
			throws IOException {
		boolean result = false;
		if (!itemClass.getSuperclass().equals(Object.class)) {
			setCsvProperties(item, itemClass.getSuperclass(), fieldValues, csvFilePath);
		}
		final Field[] fields = itemClass.getDeclaredFields();
		for (Field field : fields) {
			CsvProperty fieldCsvProperty = field.getAnnotation(CsvProperty.class);
			if (fieldCsvProperty != null) {
				Integer columnNumber = fieldCsvProperty.columnNumber();
				if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {
					result = setSimpleProperty(item, field, fieldValues[columnNumber]);
				} else if (fieldCsvProperty.propertyType() == PropertyType.CompositeProperty) {
					String keyFieldName = fieldCsvProperty.keyField();
					result = setCompositeProperty(item, field, fieldValues[columnNumber], keyFieldName, csvFilePath);
				}
				if (!result) {
					break;
				}
			}
		}
		return result;
	}

}

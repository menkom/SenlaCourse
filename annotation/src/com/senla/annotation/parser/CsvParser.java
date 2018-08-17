package com.senla.annotation.parser;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;

public class CsvParser {

	public static void exportItemCsv(Object item)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
//		String valueSeparator;
//		String entityId;
		Class<? extends Object> itemClass = item.getClass();
		if (itemClass != null) {
			CsvEntity itemCsvEntity = (CsvEntity) itemClass.getAnnotation(CsvEntity.class);
			if (itemCsvEntity != null) {
//				valueSeparator = itemCsvEntity.valueSeparator();
//				entityId = itemCsvEntity.entityId();

				SortedMap<Integer, String> data = getCsvProperties(item, itemClass);
				printMap(data);
			}
		}
	}

	public static SortedMap<Integer, String> getCsvProperties(Object item, Class<?> itemClass)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SortedMap<Integer, String> result = new TreeMap<Integer, String>();

		if (!itemClass.getSuperclass().equals(Object.class)) {
			result.putAll(getCsvProperties(item, itemClass.getSuperclass()));
		}

		final Field[] fields = itemClass.getDeclaredFields();

		for (Field field : fields) {

			CsvProperty fieldCsvProperty = field.getAnnotation(CsvProperty.class);

			if (fieldCsvProperty != null) {
				if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {

					Integer columnNumber = Integer.valueOf(fieldCsvProperty.columnNumber());

					String itemValue = getValue(item, field);

					result.put(columnNumber, itemValue);
				}
				if (fieldCsvProperty.propertyType() == PropertyType.CompositeProperty) {
					Integer columnNumber = Integer.valueOf(fieldCsvProperty.columnNumber());
					String keyFieldName = fieldCsvProperty.keyField();

					boolean isAccessible = field.isAccessible();
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}

					Class<? extends Object> fieldClass = field.get(item).getClass();

					String itemValue = getKeyValue(field.get(item), fieldClass, keyFieldName);

					result.put(columnNumber, itemValue);

					field.setAccessible(isAccessible);

				}
			}
		}
		return result;
	}

	private static void printMap(SortedMap<Integer, String> map) {
		for (SortedMap.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("Key:" + entry.getKey() + "; Value:" + entry.getValue());
		}
	}

	private static String getValue(Object item, Field field) throws IllegalArgumentException, IllegalAccessException {
		String result = null;
		boolean isAccessible = field.isAccessible();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		if (field.getType().equals(Date.class)) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			result = formatter.format(field.get(item));
		} else {
			result = field.get(item).toString();
		}
		field.setAccessible(isAccessible);
		return result;
	}

	private static String getKeyValue(Object item, Class<?> itemClass, String keyFieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		String result = null;
		if (hasField(item, itemClass, keyFieldName)) {
			Field keyField = itemClass.getDeclaredField(keyFieldName);
			result = getValue(item, keyField);
		} else {
			result = getKeyValue(item, itemClass.getSuperclass(), keyFieldName);
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

}
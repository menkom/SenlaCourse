package com.senla.annotation.parser;

import java.lang.reflect.Field;
import java.util.SortedMap;
import java.util.TreeMap;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;

public class CsvParser {

	public static void exportItemCsv(Object item) {
//		StringBuilder result = new StringBuilder();
		SortedMap<Integer, String> result = new TreeMap<>();
		@SuppressWarnings("unused")
		String valueSeparator;
		@SuppressWarnings("unused")
		String entityId;
		Class<? extends Object> itemClass = item.getClass();
		if (itemClass != null) {
			System.out.println("itemClass.class>" + itemClass.getSimpleName());
			CsvEntity itemCsvEntity = (CsvEntity) itemClass.getAnnotation(CsvEntity.class);
			if (itemCsvEntity != null) {
				System.out.println("itemCsvEntity.toStr>" + itemCsvEntity.toString());
				valueSeparator = itemCsvEntity.valueSeparator();
				entityId = itemCsvEntity.entityId();
				final Field[] fields = itemClass.getDeclaredFields();
				System.out.println("fields.length>" + fields.length);
				for (Field field : fields) {
					System.out.println(field.getName());
					CsvProperty fieldCsvProperty = field.getAnnotation(CsvProperty.class);
					if (fieldCsvProperty != null) {
						System.out.println("fieldCsvProperty.toStr>" + fieldCsvProperty.toString());
						if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {
							try {
								boolean isAccessible = field.isAccessible();
								if (!field.isAccessible()) {
									field.setAccessible(true);
								}
								System.out.println("field>" + field.getName() + "==" + field.get(item));
								result.put(fieldCsvProperty.columnNumber(), (String) field.get(item));
								field.setAccessible(isAccessible);

							} catch (IllegalArgumentException | IllegalAccessException e) {
								System.out.println(e);
							}
						}
					}
				}
			}
		}
	}

	public static SortedMap<Integer, String> getCsvProperty(Object item, Class<?> itemClass) {
		SortedMap<Integer, String> result = new TreeMap<>();
		final Field[] fields = itemClass.getDeclaredFields();

		System.out.println("fields.length>" + fields.length);
		for (Field field : fields) {

			System.out.println(field.getName());
			CsvProperty fieldCsvProperty = field.getAnnotation(CsvProperty.class);

			if (fieldCsvProperty != null) {
				System.out.println("fieldCsvProperty.toStr>" + fieldCsvProperty.toString());
				if (fieldCsvProperty.propertyType() == PropertyType.SimpleProperty) {
					try {

						boolean isAccessible = field.isAccessible();
						if (!field.isAccessible()) {
							field.setAccessible(true);
						}
						System.out.println("field>" + field.getName() + "==" + field.get(item));
						result.put(fieldCsvProperty.columnNumber(), (String) field.get(item));
						field.setAccessible(isAccessible);

					} catch (IllegalArgumentException | IllegalAccessException e) {
						System.out.println(e);
					}
				}
			}
		}
		if (!itemClass.getSuperclass().equals(Object.class)) {
			result.putAll(getCsvProperty(item, itemClass.getSuperclass()));
		}
		return result;
	}

}
package com.senla.annotation.parser;

import com.senla.annotation.CsvEntity;

public class CsvParser {

	public static void exportItemCsv(Object item) {
		StringBuilder sb = new StringBuilder();
		String valueSeparator;
		String entityId;
		Class itemClass = item.getClass();
		if (itemClass != null) {
			CsvEntity itemCsvEntity = (CsvEntity) itemClass.getAnnotation(CsvEntity.class);
			if (itemCsvEntity != null) {
			
			}
		}
	}
}

package com.senla.annotation.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.parser.CsvExport;
import com.senla.annotation.parser.CsvImport;

public class CsvParser {

	public static boolean exportToCsv(List<?> list, String csvFilePath) {
		List<String> toSave = new ArrayList<>();
		String valueSeparator = "";
		String filename = "";
		for (Object item : list) {
			Class<? extends Object> itemClass = item.getClass();

			if (itemClass != null) {
				CsvEntity itemCsvEntity = (CsvEntity) itemClass.getAnnotation(CsvEntity.class);
				if (itemCsvEntity != null) {
					if (filename.equals("")) {
						filename = itemCsvEntity.filename();
					}

					if (valueSeparator.equals("")) {
						valueSeparator = itemCsvEntity.valueSeparator();
					}

					SortedMap<Integer, String> data = CsvExport.getCsvProperties(item, itemClass, valueSeparator);

					toSave.add(CsvExport.mapToString(data, valueSeparator));
				}
			}
		}
		return CsvExport.saveCsvFile(toSave, csvFilePath + filename);
	}

	public static <T extends Object> List<T> importFromCsv(Class<T> itemClass, String csvFilePath) {
		return CsvImport.getFromCsv(itemClass, csvFilePath);
	}

}
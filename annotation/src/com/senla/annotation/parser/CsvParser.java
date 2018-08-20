package com.senla.annotation.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.parser.CsvExport;
import com.senla.annotation.parser.CsvImport;

public class CsvParser {

	public static boolean exportToCsv(List<?> list, String csvFilePath) throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException, IOException {
		boolean result = false;
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

		CsvExport.saveCsvFile(toSave, csvFilePath + filename);

		result = true;
		return result;
	}

	public static <T extends Object> List<T> importFromCsv(Class<T> itemClass, String csvFilePath) throws IOException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, ParseException {
		List<T> result = new ArrayList<>();
		if (itemClass != null) {
			CsvEntity itemCsvEntity = (CsvEntity) itemClass.getAnnotation(CsvEntity.class);
			if (itemCsvEntity != null) {
				String valueSeparator = itemCsvEntity.valueSeparator();
				String filename = itemCsvEntity.filename();
				List<String> csvList = CsvImport.loadCsvFile(csvFilePath + filename);

				for (String line : csvList) {
					T item = itemClass.newInstance();
					String[] array = line.split(valueSeparator);

					if (CsvImport.setCsvProperties(item, itemClass, array)) {
						result.add(item);
					}
				}

			}

		}
		System.out.println(result);
		return result;
	}

}
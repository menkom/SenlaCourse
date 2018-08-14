package com.senla.ui.util;

import java.io.File;

public class FileCheck {

	public static boolean isFileExist(String filePath) {
		return new File(filePath).isFile();
	}
}

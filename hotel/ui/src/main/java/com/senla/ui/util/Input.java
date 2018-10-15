package com.senla.ui.util;

import java.util.Scanner;

public class Input {

	public static Integer inputInteger() {
		Integer result = null;
		try (Scanner sc = new Scanner(new UnClosableDecorator(System.in))) {
			result = Integer.parseInt(sc.nextLine());
		}
		return result;
	}

	public static String inputString() {
		String result = null;
		try (Scanner sc = new Scanner(new UnClosableDecorator(System.in))) {
			result = sc.nextLine();
		}
		return result;
	}
}

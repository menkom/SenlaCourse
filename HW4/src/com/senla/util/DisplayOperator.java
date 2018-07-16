package com.senla.util;

import java.util.Arrays;

import com.senla.hotel.repository.IBaseRepository;
import com.senla.hotel.services.IService;

public class DisplayOperator {

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printRepository(IBaseRepository array) {
		printMessage(Arrays.toString(array.toStringArray()));
	}

	public static void printService(IService service) {
		printRepository(service.getRepository());
	}
}

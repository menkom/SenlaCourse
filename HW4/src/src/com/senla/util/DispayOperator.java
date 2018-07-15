package com.senla.util;

import java.util.Arrays;

import com.senla.hotel.array.IBaseArray;
import com.senla.hotel.services.IService;

public class DispayOperator {

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void printRepository(IBaseArray array) {
		printMessage(Arrays.toString(array.toStringArray()));
	}

	public void printService(IService service) {
		printRepository(service.getRepository());
	}
}

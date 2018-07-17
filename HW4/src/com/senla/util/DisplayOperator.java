package com.senla.util;

import java.util.Arrays;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Room;
import com.senla.hotel.repository.IBaseRepository;
import com.senla.hotel.services.IService;

public class DisplayOperator {

	public static String SEPARATOR_LINE = "=====================================";

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printArray(BaseObject[] array) {
		printMessage(Arrays.toString(array));
	}

	public static void printRepository(IBaseRepository repository) {
		printMessage(Arrays.toString(repository.toStringArray()));
	}

	public static void printService(IService service) {
		printRepository(service.getRepository());
	}

	public static void printRoomInfo(Room room) {
		if (room == null) {
			printMessage("Room: Number= -, Capacity= -, Star= -, Status= -, Price= -");
		} else {
			printMessage("Room: Number=" + room.getNumber() + ", Capacity=" + room.getCapacity() + ", Star="
					+ room.getStar() + ", Status=" + room.getStatus() + ", Price=" + room.getPrice());
		}
	}
}

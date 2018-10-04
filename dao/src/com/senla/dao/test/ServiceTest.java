package com.senla.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.senla.dao.DaoHandler;
import com.senla.hotel.dao.ServiceDao;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.api.IServiceRepository;

public class ServiceTest {

	public static void testServiceDao() {
		IServiceRepository services = (ServiceDao) ServiceDao.getInstance();
		// getAll
		System.out.println("services : " + services.getServices().size());
		// insert
		Service service = new Service(6, "service code", 66);
//		System.out.println("Service add:" + services.add(service));

		// addAll
		List<Service> servicesToAdd = new ArrayList<>();
		servicesToAdd.add(new Service(701, "service code 1", 71));
		servicesToAdd.add(new Service(702, "service code 2", 72));
		servicesToAdd.add(new Service(703, "service code 3", 73));
//		System.out.println("Services add all:" + services.addAll(servicesToAdd));

		// deleteById
//		for (int i = 6; i <= 35; i++) {
//			services.deleteById(i);
//		}

		// getById
		System.out.println("service[2] : " + services.getServiceById(3));

		// update
		service = services.getServices().get(0);
		service.setName(service.getName() + "1");
//		services.update(service);
		System.out.println("service[0] : " + service.getName());
		DaoHandler.getInstance().closeConnection();
	}
}

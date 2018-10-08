package com.senla.dao.test;

import java.sql.SQLException;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.hotel.dao.ServiceDao;
import com.senla.hotel.model.Service;

public class ServiceTest {

	public static void testServiceDao() throws ClassNotFoundException {
		ServiceDao dao = new ServiceDao();
		// getAll
		try {
			List<Service> services = dao.getAll(DbConnector.getInstance().getConnection(), "");
			for (Service service : services) {
				System.out.println(service);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		// getById
		try {
			System.out.println("service[2] : " + dao.getById(DbConnector.getInstance().getConnection(), 3));
		} catch (SQLException e) {
			System.out.println(e);
		}

//		// add
//		try {
//			System.out.println(
//					"entity add : " + dao.add(DbConnector.getInstance().getConnection(), new Service(701, "", 701)));
//		} catch (SQLException e) {
//			System.out.println(e);
//		}

		// deleteById
		try {
			System.out.println("delete entity.id=6 : " + dao.delete(DbConnector.getInstance().getConnection(), 6));
		} catch (SQLException e) {
			System.out.println(e);
		}

		// update
		try {
			Service service = dao.getById(DbConnector.getInstance().getConnection(), 1);
			service.setName(service.getName() + "1");
			System.out
					.println("service.id=1 update: " + dao.update(DbConnector.getInstance().getConnection(), service));
		} catch (SQLException e) {
			System.out.println(e);
		}

		// IServiceRepository services = (ServiceDao) ServiceDao.getInstance();
//		// getAll
//		System.out.println("services : " + services.getServices().size());
//		// insert
//		Service service = new Service(6, "service code", 66);
////		System.out.println("Service add:" + services.add(service));
//
//		// addAll
//		List<Service> servicesToAdd = new ArrayList<>();
//		servicesToAdd.add(new Service(701, "service code 1", 71));
//		servicesToAdd.add(new Service(702, "service code 2", 72));
//		servicesToAdd.add(new Service(703, "service code 3", 73));
////		System.out.println("Services add all:" + services.addAll(servicesToAdd));
//
//		// deleteById
////		for (int i = 6; i <= 35; i++) {
////			services.deleteById(i);
////		}
//
//		// getById
//		System.out.println("service[2] : " + services.getServiceById(3));
//
//		// update
//		service = services.getServices().get(0);
//		service.setName(service.getName() + "1");
////		services.update(service);
//		System.out.println("service[0] : " + service.getName());
//		DaoHandler.getInstance().closeConnection();
	}
}

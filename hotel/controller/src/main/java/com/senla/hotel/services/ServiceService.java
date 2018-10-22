package com.senla.hotel.services;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IServiceDao;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.model.Service;
import com.senla.hotel.services.api.IServiceService;
import com.senla.util.ExportCSV;

public class ServiceService implements IServiceService {

	private final static Logger logger = Logger.getLogger(ServiceService.class);

	private static IServiceService serviceService;

	private DbConnector dbConnector;

	private IServiceDao<Service> serviceDao;

	@SuppressWarnings("unchecked")
	private ServiceService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.serviceDao = DependencyInjection.getInstance().getInterfacePair(IServiceDao.class);
	}

	public static IServiceService getInstance() {
		if (serviceService == null) {
			serviceService = DependencyInjection.getInstance().getInterfacePair(IServiceService.class);
		}
		return serviceService;
	}

	@Override
	public void add(Service service) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			serviceDao.add(session, service);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void addAll(List<Service> services) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			serviceDao.addAll(session, services);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void addService(Service service) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			serviceDao.add(session, service);

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void update(Service service) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			serviceDao.update(session, service);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public List<Service> getServices(EnumServiceSort serviceSort) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			List<Service> services = serviceDao.getAll(session, serviceSort.getTableField());
			session.getTransaction().commit();
			return services;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public Service getServiceById(int serviceId) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Service result = serviceDao.getById(session, serviceId);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {

			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void changeServicePrice(int serviceId, int price) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Service service = serviceDao.getById(session, serviceId);
			if (service != null) {
				service.setPrice(price);
				serviceDao.update(session, service);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}

	}

	@Override
	public boolean exportServiceCSV(int serviceId, String fileName) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Service service = serviceDao.getById(session, serviceId);
			if (service != null) {
				result = ExportCSV.saveCSV(service.toString(), fileName);
			}

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean importServicesCSV(String file) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Service> rooms = ExportCSV.getServicesFromCSV(file);
			for (Service room : rooms) {
				if (serviceDao.getById(session, room.getId()) != null) {
					serviceDao.update(session, room);
				} else {
					serviceDao.add(session, room);
				}
			}

			session.getTransaction().commit();
			result = true;
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			result = serviceDao.exportCsv(session, csvFilePath);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			result = serviceDao.importCsv(session, csvFilePath);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}

package com.senla.hotel.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.senla.annotation.parser.CsvParser;
import com.senla.base.BaseObject;
import com.senla.hotel.dao.api.IGenericDao;

public abstract class GenericDao<T extends BaseObject> implements IGenericDao<T> {

	protected abstract Class<T> getTClass();

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Session session, String sortColumn) {
		Criteria cr = session.createCriteria(getTClass());
		if ((sortColumn != null) && (!sortColumn.equals(""))) {
			cr.addOrder(Order.asc(sortColumn));
		}
		return cr.list();
	}

	@Override
	public void add(Session session, T entity) {
		session.save(entity);
	}

	@Override
	public void addAll(Session session, List<T> list) {
		for (T entity : list) {
			session.save(entity);
		}
	}

	@Override
	public void update(Session session, T entity) {
		session.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Session session, int id) {
		return (T) session.get(getTClass(), id);
	}

	@Override
	public void delete(Session session, T entity) {
		session.delete(entity);
	}

	@Override
	public boolean exportCsv(Session session, String csvFilePath) throws IOException {
		return CsvParser.exportToCsv(getAll(session, ""), csvFilePath);
	}

	@Override
	public boolean importCsv(Session session, String csvFilePath) throws IOException {
		boolean result = false;
		addAll(session, CsvParser.importFromCsv(getTClass(), csvFilePath));
		result = true;
		return result;
	}

}

package com.senla.hotel.dao.api;

import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import com.senla.base.BaseObject;

public interface IGenericDao<T extends BaseObject> {

	void add(Session session, T entity);

	void update(Session session, T entity);

	List<T> getAll(Session session, String sortColumn);

	void addAll(Session session, List<T> list);

	T getById(Session session, int id);

	void delete(Session session, T entity);

	boolean exportCsv(Session session, String csvFilePath) throws IOException;

	boolean importCsv(Session session, String csvFilePath) throws IOException;

}

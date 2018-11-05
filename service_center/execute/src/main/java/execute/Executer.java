package execute;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import dao.HibernateUtil;
import model.PartCategory;

public class Executer {

	private static final Logger logger = Logger.getLogger(Executer.class);

	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			PartCategory partCategory = new PartCategory();
			partCategory.setName("NameCat");

			session.save(partCategory);

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
		HibernateUtil.shutdown();
	}

}

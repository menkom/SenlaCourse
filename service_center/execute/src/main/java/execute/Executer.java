package execute;

import org.hibernate.Session;

import dao.HibernateUtil;
import model.PartCategory;

public class Executer {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Check database version
		String sql = "select version()";

		String result = (String) session.createNativeQuery(sql).getSingleResult();
		System.out.println(result);

		//Testing models
		PartCategory partCategory = new PartCategory();
		partCategory.setName("NameCat");
		
		
		session.getTransaction().commit();
		session.close();

		HibernateUtil.shutdown();
	
	}

}

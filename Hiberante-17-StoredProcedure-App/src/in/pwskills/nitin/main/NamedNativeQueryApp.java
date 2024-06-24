package in.pwskills.nitin.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.pwskills.nitin.bean.Product;
import in.pwskills.nitin.util.HibernateUtil;

public class NamedNativeQueryApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();
			NativeQuery<Product> query = session.getNamedNativeQuery("callStoredProcedure");
			query.setParameter("product1", "OMEGA");
			query.setParameter("product2", "tissot");

			List<Product> list = query.getResultList();
			list.forEach(System.out::println);
			
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			HibernateUtil.closeSessionFactory();
			if (session != null) {
				session.close();
			}
		}

	}

}

package in.pwskills.nitin.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.pwskills.nitin.bean.InsurancePolicy;
import in.pwskills.nitin.util.HibernateUtil;

public class NativeSQLApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {

			// EntityQuery -> NATIVE SQL QUERY WITHOUT MAPPING TO ENTITY CLASS
			session = HibernateUtil.getSession();
			NativeQuery<InsurancePolicy> query1 = session
					.createSQLQuery("select * from insurancePolicy where tenure>=:max1 and tenure<=:max2");

			// setting the value to namedparameter(max1 and max2)
			query1.setParameter("max1", 20);
			query1.setParameter("max2", 40);

			//Mapping the Entity class to Table
			query1.addEntity(InsurancePolicy.class);

			// execute the query and get the Result
			List<InsurancePolicy> list = query1.getResultList();
			list.forEach(System.out::println);

			System.out.println("*********************************************");

			// EntityQuery -> NATIVE SQL QUERY WITHOUT MAPPING TO ENTITY CLASS	
			NativeQuery<Object[]> query2 = session
					.createSQLQuery("select * from insurancePolicy where tenure>=:max1 and tenure<=:max2");

			// setting the value to namedparameter(max1 and max2)
			query2.setParameter("max1", 20);
			query2.setParameter("max2", 40);

			// execute the query and get the Result
			List<Object[]> objects = query2.getResultList();
			objects.forEach(object -> {
				for (Object data : object) {
					System.out.print(data + " ");
				}
				System.out.println();
			});

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

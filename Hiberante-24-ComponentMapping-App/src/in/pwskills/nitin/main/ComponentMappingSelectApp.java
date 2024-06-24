package in.pwskills.nitin.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.StudentInfo;
import in.pwskills.nitin.util.HibernateUtil;

public class ComponentMappingSelectApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();
			Query<StudentInfo> query = session.createQuery("from StudentInfo where address.cityName=:city");
			query.setParameter("city", "Bengaluru");
			List<StudentInfo> students = query.getResultList();
			students.forEach(System.out::println);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}

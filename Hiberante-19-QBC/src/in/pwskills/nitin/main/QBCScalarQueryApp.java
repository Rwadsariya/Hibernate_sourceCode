package in.pwskills.nitin.main;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.Project;
import in.pwskills.nitin.util.HibernateUtil;

public class QBCScalarQueryApp {

	public static void main(String[] args) {
		Session session = null;


//		try {
//			// select projName from project where cost between(?,?) order by projName asc
//			session = HibernateUtil.getSession();
//
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
//			Root<Project> root = criteriaQuery.from(Project.class);
//
//			criteriaQuery.multiselect(root.get("projName"))
//					     .where(builder.between(root.get("cost"), 20000, 300000))
//					     .orderBy(builder.asc(root.get("projName")));
//
//			Query<String> query = session.createQuery(criteriaQuery);
//			List<String> list = query.getResultList();
//			list.forEach(System.out::println);
//
//		} catch (HibernateException he) {
//			he.printStackTrace();
//		} finally {
//			HibernateUtil.closeSession(session);
//			HibernateUtil.closeSessionFactory();
//		}

		try {
			// SQL :: select count(*) from project
			session = HibernateUtil.getSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Project> root = criteriaQuery.from(Project.class);

			criteriaQuery.multiselect(builder.count(root.get("projId")));

			Query<Long> query = session.createQuery(criteriaQuery);
			Optional<Long> optional = query.uniqueResultOptional();
			long count = optional.isPresent() ? optional.get() : 0;
			System.out.println("No of records is :: " + count);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}

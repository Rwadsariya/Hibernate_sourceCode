package in.pwskills.nitin.main;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.Project;
import in.pwskills.nitin.util.HibernateUtil;

public class QBDDeleteApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag= false;
		long count = 0;

		try {
			//delete from project where cost between(27000 , 45000)
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaDelete<Project> criteriaDelete = builder.createCriteriaDelete(Project.class);
			Root<Project> root = criteriaDelete.from(Project.class);
			
			//delete operation
			criteriaDelete.where(builder.between(root.get("cost"), 27000, 45000));
			Query<Integer> query = session.createQuery(criteriaDelete);
			count = query.executeUpdate();
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("No of records deleted is :: "+count);
			} else {
				transaction.rollback();
				System.out.println("No records found to delete");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}

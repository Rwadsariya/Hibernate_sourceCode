package in.pwskills.nitin.main;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.Project;
import in.pwskills.nitin.util.HibernateUtil;

public class QBCUpdateApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag= false;
		long count = 0;

		try {
			//update project set teamsize = 20 ,location ='hyd' where cost>25000;
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaUpdate<Project> criteriaUpdate = builder.createCriteriaUpdate(Project.class);
			Root<Project> root = criteriaUpdate.from(Project.class);
			
			//update operation
			criteriaUpdate.set(root.get("teamSize"),20)
					      .set(root.get("location"), "hyd")
					      .where(builder.ge(root.get("cost"), 25000));
			
			
			Query<Integer> query = session.createQuery(criteriaUpdate);
			count = query.executeUpdate();
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("No of records updated is :: "+count);
			} else {
				transaction.rollback();
				System.out.println("No records found to update");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}

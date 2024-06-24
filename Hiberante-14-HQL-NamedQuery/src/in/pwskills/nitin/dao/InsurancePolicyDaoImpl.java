package in.pwskills.nitin.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.pwskills.nitin.util.HibernateUtil;

public class InsurancePolicyDaoImpl implements InsurancePolicyDao {

	@Override
	public String transferPolicies(int minTenure) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		@SuppressWarnings("rawtypes")
		Query query = null;

		long count = 0;
		String msg = null;

		try {

			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			// Buisness Logic
			query = session.getNamedQuery("HQL_TRANSFER_POLICIES");
			query.setParameter("min", minTenure);
			count = query.executeUpdate();

			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {

			if (flag) {
				transaction.commit();
				msg = "No of records copied are :: " + count;
			} else {
				transaction.rollback();
				msg = "Records not copied/inserted";
			}

			HibernateUtil.closeSessionFactory();
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}
}

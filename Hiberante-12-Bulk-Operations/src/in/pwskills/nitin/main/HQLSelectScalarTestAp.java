package in.pwskills.nitin.main;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.InsurancePolicy;
import in.pwskills.nitin.util.HibernateUtil;

public class HQLSelectScalarTestAp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		List<Object[]> listOfRecords = null;

		@SuppressWarnings("rawtypes")
		Query query = null;
		try {

			session = HibernateUtil.getSession();
			query = session.createQuery(
					"SELECT policyId,policyName FROM in.pwskills.nitin.bean.InsurancePolicy WHERE policyId=:id");

			// set namedparameter for id
			long id = 2L;
			query.setParameter("id", id);

			listOfRecords = query.getResultList();
			System.out.println(listOfRecords.size());
			if (!listOfRecords.isEmpty()) {
				// print the records
				Object[] object = listOfRecords.get(0);
				System.out.println(object[0] + " " + object[1]);

			} else {
				System.out.println("Record not available for the give id :: " + id);
			}

			System.out.println("*********************************");
			query = session.createQuery("SELECT count(*) FROM in.pwskills.nitin.bean.InsurancePolicy");
			List<?> list = query.getResultList();
			if (!list.isEmpty()) {
				// print the records
				long count = (long) list.get(0);
				System.out.println("No of records is :: " + count);
			}
			System.out.println();

			System.out.println("*****************************");
			query = session.createQuery("FROM in.pwskills.nitin.bean.InsurancePolicy where policyId=:id");
			query.setParameter("id", 4L);
			Optional<InsurancePolicy> optional = query.uniqueResultOptional();
			if (optional.isPresent()) {
				InsurancePolicy policy = optional.get();
				System.out.println(policy);
			} else {
				System.out.println("Record not found...");
			}

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

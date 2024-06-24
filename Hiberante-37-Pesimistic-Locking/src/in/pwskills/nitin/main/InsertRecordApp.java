package in.pwskills.nitin.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.InsurancePolicy;
import in.pwskills.nitin.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();

		Transaction transaction = session.beginTransaction();
		InsurancePolicy policy = new InsurancePolicy();
		policy.setCompany("SBI");
		policy.setPolicyName("SBIANAND");
		policy.setPolicyType("quarter");
		policy.setTenure(10);
		session.save(policy);
		transaction.commit();

	}

}

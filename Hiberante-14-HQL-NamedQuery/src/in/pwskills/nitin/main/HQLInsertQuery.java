package in.pwskills.nitin.main;

import in.pwskills.nitin.dao.InsurancePolicyDao;
import in.pwskills.nitin.dao.InsurancePolicyDaoImpl;

public class HQLInsertQuery {

	public static void main(String[] args) {

		InsurancePolicyDao dao = new InsurancePolicyDaoImpl();
		System.out.println(dao.transferPolicies(46));

	}
}

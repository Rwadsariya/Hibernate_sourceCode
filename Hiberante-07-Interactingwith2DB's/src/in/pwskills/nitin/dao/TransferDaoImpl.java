package in.pwskills.nitin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.Product;
import in.pwskills.nitin.util.MySQLHibernateUtil;
import in.pwskills.nitin.util.OracleHibernateUtil;

public class TransferDaoImpl implements TransferDao {

	@SuppressWarnings("finally")
	@Override
	public String transferProductById(Integer id) {

		Session oracleSession = null, mysqlSession = null;
		Transaction mysqlTransaction = null;
		String status = "";
		boolean flag = false;

		oracleSession = OracleHibernateUtil.getSession();

		// get the record from oracle based on id
		Product product = oracleSession.get(Product.class, id);
		
		if (product != null) {
			// send to MYSQL and perform save operation

			try {
				mysqlSession = MySQLHibernateUtil.getSession();
				mysqlTransaction = mysqlSession.beginTransaction();

				mysqlSession.save(product);
				flag = true;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (mysqlTransaction != null) {
					if (flag) {
						mysqlTransaction.commit();
						return "record copied from oracle to mysql...";
					} else {
						mysqlTransaction.rollback();
						return "record not copied from oracle to mysql...";
					}

				}
				return null;
			}

		} else {
			status = " record not found";
			return status;
		}

	}

}

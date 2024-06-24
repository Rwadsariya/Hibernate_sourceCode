package in.pwskills.nitin.main;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.CardPayment;
import in.pwskills.nitin.bean.ChequePayment;
import in.pwskills.nitin.util.HibernateUtil;

public class TBSInsertApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			CardPayment cardPayment = new CardPayment();
			cardPayment.setCardNo(1234L);
			cardPayment.setCardType("debit");
			cardPayment.setPaymentGateWay("visa");
			cardPayment.setAmount(8000.0f);

			session.save(cardPayment);

			ChequePayment chequePayment = new ChequePayment();
			chequePayment.setAmount(9000.0f);
			chequePayment.setChequeNo(56456L);
			chequePayment.setChequeType("all");
			chequePayment.setExpriyDate(LocalDate.of(2025, 10, 25));

			session.save(chequePayment);
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record saved to database...");
			} else {
				transaction.rollback();
				System.out.println("Record not saved to database...");
			}
		}

	}
}

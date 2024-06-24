package in.pwskills.nitin.main;

import in.pwskills.nitin.dao.IPassportDao;
import in.pwskills.nitin.dao.PassportDaoImpl;

public class MainApp {

	public static void main(String[] args) {

		IPassportDao dao = new PassportDaoImpl();
		dao.loadRecordUsingParent();
	}
}

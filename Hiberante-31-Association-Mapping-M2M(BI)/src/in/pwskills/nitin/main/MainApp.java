package in.pwskills.nitin.main;

import in.pwskills.nitin.dao.IHospitalDao;
import in.pwskills.nitin.dao.HospitalDaoImpl;

public class MainApp {

	public static void main(String[] args) {

		IHospitalDao dao = new HospitalDaoImpl();
		dao.loadRecordUsingParent();
	}
}

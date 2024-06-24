package in.pwskills.nitin.main;

import in.pwskills.nitin.dao.IPersonDao;
import in.pwskills.nitin.dao.PersonDaoImpl;

public class MainApp {

	public static void main(String[] args) {

		IPersonDao dao = new PersonDaoImpl();
		dao.deleteParentAndItsChilds();
	}
}

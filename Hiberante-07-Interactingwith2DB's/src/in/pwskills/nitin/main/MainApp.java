package in.pwskills.nitin.main;

import in.pwskills.nitin.dao.TransferDao;
import in.pwskills.nitin.dao.TransferDaoImpl;

public class MainApp {

	public static void main(String[] args) {

		TransferDao dao = null;
		dao = new TransferDaoImpl();
		System.out.println("status:: "+dao.transferProductById(299));

	}
}

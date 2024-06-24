package in.pwskills.nitin.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.pwskills.nitin.bean.ProgramProjId;
import in.pwskills.nitin.bean.ProgrammerProjectInfo;
import in.pwskills.nitin.util.HibernateUtil;

public class LoadApp {

	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			ProgramProjId id = new ProgramProjId();
			id.setPid(101);
			id.setProjId(501);

			ProgrammerProjectInfo info = session.get(ProgrammerProjectInfo.class, id);
			if (info != null) {
				System.out.println(info);
			} else {
				System.out.println("Record not found for the id :: " + id);
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

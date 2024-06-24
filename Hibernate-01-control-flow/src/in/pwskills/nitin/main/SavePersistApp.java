package in.pwskills.nitin.main;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.pwskills.nitin.beans.Student;
import in.pwskills.nitin.util.HibernateUtil;

public class SavePersistApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Transaction txc =  null;
		Session session = null;
		boolean flag = false;
		Integer id;
		
		try {
			session = HibernateUtil.getSession();
			if (session != null)
				txc = session.beginTransaction();
			
			if (txc != null) {
				Student std =  new Student();
				std.setSname("Dravid");
				std.setSage(56);
				std.setSaddress("RCB");
				
				id  = (Integer)session.save(std); // insert Query :: referring to Mapping information.
				System.out.println(id);
				flag = true;
			}
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally {
			
				try {
					if(flag) {
					System.in.read();
					txc.commit();
					} else {
						txc.rollback();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (session != null)
					session.close();
		}
	}

}

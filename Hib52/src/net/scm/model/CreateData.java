package net.scm.model;

import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.scm.model.*;

public class CreateData {
	public static void main(String[] args) throws Exception {

		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();
		
		Employee emp = new Employee();
		emp.setEmpName("Mukund");
		emp.setEmpMobileNos("000002");
		emp.setEmpAddress("KRP - India");
		session.save(emp);
		System.out.println("Successfully inserted-1");
		tr.commit();
		
		Employee emp1 = new Employee();
		emp1.setEmpName("Mukund1");
		emp1.setEmpMobileNos("000003");
		emp1.setEmpAddress("KRP - India");
		session.save(emp1);
		
		tr.commit();
		System.out.println("Successfully inserted-2");
		sessFact.close();
		
/*
 		TimeUnit.SECONDS.sleep(20);

		
		SessionFactory sessFact1 = HibernateUtil.getSessionFactory();
		Session session1 = sessFact.getCurrentSession();
		org.hibernate.Transaction tr1 = session.beginTransaction();
		Employee emp1 = new Employee();
		emp1.setEmpName("Mukund1");
		emp1.setEmpMobileNos("000003");
		emp1.setEmpAddress("KRP - India");
		session1.save(emp1);
		tr1.commit();
		System.out.println("Successfully inserted-2");
		sessFact1.close();
		 */
		
	}

}
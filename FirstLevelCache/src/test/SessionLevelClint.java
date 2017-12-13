package test;

import java.io.Serializable;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Student;

public class SessionLevelClint {

	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("resources/Hibernate.cfg.xml");
		SessionFactory sf =  cfg.buildSessionFactory();
		Session s =sf.openSession();
		Transaction t =  s.beginTransaction();
		
		//table must be there in database to explain this
		//System.out.println("Table creation success");
	
		/*//data must be there in the table
		Student st = new Student();
		st.setAddress("bang");
		st.setId(22);
		st.setName("laxmi");
		Serializable pk = s.save(st);
		System.out.println("pk is : "+pk);
		t.commit();*/
		
		
		//we r trying to get the same record multiple times
		//s0 it will only fire sql query one time : see the format
		//but if we want two different record(different id ) then it will fire two times
		
		//means when session is same cache is applied else not
		Student st1= (Student)s.get(Student.class, 111);
		System.out.println("Session level cache(first level cache)");
		System.out.println(st1.getId());
		System.out.println(st1.getAddress());
		System.out.println(st1.getName());
		
		Student st2= (Student)s.get(Student.class, 111);
		System.out.println(st2.getId());
		System.out.println(st2.getAddress());
		System.out.println(st2.getName());
		
		
	}
}

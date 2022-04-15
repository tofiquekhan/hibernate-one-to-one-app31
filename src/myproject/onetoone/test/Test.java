package myproject.onetoone.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import myproject.onetoone.entity.Account;
import myproject.onetoone.entity.Employee;

public class Test {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/myproject/onetoone/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Account acc = new Account();
			acc.setAccNo("abc123");
			acc.setAccName("AAA");
			acc.setAccType("Saving");
			acc.setBalance(10000);
			
			Employee emp = new Employee();
			emp.setEno(111);
			emp.setEname("AAAA");
			emp.setEsal(5000);
			emp.setEaddr("IND");
			emp.setAcc(acc);
			
			int pk = (Integer) session.save(emp);
			tx.commit();
			System.out.println("Employee Pk is : "+pk);
			
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
		
		
		
	}
}

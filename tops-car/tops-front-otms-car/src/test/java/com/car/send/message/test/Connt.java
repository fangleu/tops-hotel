package com.car.send.message.test;

import java.util.Date;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.car.bean.Level;

@Component
@Transactional
public class Connt {
	
	
	@SuppressWarnings("resource")
	@Test
	public void test(){
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-servlet.xml");
	        SessionFactory factory = (SessionFactory) context.getBean("sessionFactory");
	        Session session = factory.openSession();
	        Transaction transaction = session.beginTransaction();
	        Level lev = new Level();
            session.save(lev);
            session.flush();
			transaction.commit();
		
	}

}

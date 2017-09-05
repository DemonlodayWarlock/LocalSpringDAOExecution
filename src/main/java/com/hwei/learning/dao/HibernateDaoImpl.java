package com.hwei.learning.dao;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hwei.learning.model.Circle;

@Repository
public class HibernateDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Circle queryCircleWithId(int id) {
		
		Circle circle=null;
		//"Select circle" can be removed from SQL
		String sql = "from Circle circle where id = ?";
		Query query= getSessionFactory().openSession().createQuery(sql);
		//query.setInteger(0, id); ----- Query option1
		//query.setParameter(0, id); ------ Query option2
		query.setParameters(new Object[] {id}, new Type[] {new IntegerType()});//Query option3
		circle= (Circle)query.uniqueResult();
		System.out.println("Circle:"+circle.toString());
		return circle;
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

package com.hwei.learning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.hwei.learning.dao.CircleResposity;
import com.hwei.learning.dao.HibernateDaoImpl;
import com.hwei.learning.dao.JdbcTemplateImpl;
import com.hwei.learning.model.Circle;
import com.hwei.learning.service.CircleServiceImpl;

public class DataAccess {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		//CircleResposity reposity = context.getBean("circleResposity",CircleResposity.class); 
		
		//System.out.println("Spring jdbc:"+reposity.queryCircleById("First record",1).getName());
		
		//System.out.println("Spring JdbcTemplate:"+reposity.queryCircleById(1).getName());
		
		//System.out.println("Spring RowMapper to query object:"+reposity.queryCircleWithId(3).getName());
		
		//System.out.println("Spring RowMapper to query List"+reposity.queryCircles().toString());
		
		//reposity.updateCircleName("Already update it", 1);
		
		//System.out.println("After update:"+reposity.queryCircleById(1).getName());
		
		//reposity.insertCircle(new Circle(6,"Six record"));
		
		//System.out.println("Spring RowMapper to query List"+reposity.queryCircles().toString());
		
		//JdbcTemplateImpl jdbcImpl = context.getBean("jdbcTemplateImpl",JdbcTemplateImpl.class);
		//System.out.println(jdbcImpl.queryCircleWithId(3));
		
		HibernateDaoImpl dao = context.getBean("hibernateDaoImpl",HibernateDaoImpl.class);
		//System.out.println(dao);
		System.out.println(dao.queryCircleWithId(4).getName());
		
		
	}

}

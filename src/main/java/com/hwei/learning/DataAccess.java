package com.hwei.learning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hwei.learning.dao.CircleResposity;
import com.hwei.learning.service.CircleServiceImpl;

public class DataAccess {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		CircleResposity reposity = (CircleResposity)context.getBean("circleResposity"); 
			
		System.out.println(reposity.queryCircleById("First record",1).getName());
		
		//System.out.println(context.getBean("dataSource"));
		
	}

}

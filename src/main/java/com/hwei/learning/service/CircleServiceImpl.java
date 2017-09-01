package com.hwei.learning.service;

import com.hwei.learning.dao.CircleResposity;
import com.hwei.learning.model.Circle;

public class CircleServiceImpl {
	
	public Circle getCircle(String value,int id) {
		Circle circie = new CircleResposity().queryCircleById(value,id);	
		return circie;	
	}

}

package com.hwei.learning.model;

import java.lang.annotation.Annotation;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *  
 * If want to integrate with Hibernate, need add annotation "@Entity" for model, and add {@link Annotation} "@Id" for property id.
 * 
 */

@Entity
public class Circle {
	@Id
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Circle(int id,String name) {
		setId(id);
		setName(name);
	}
	
	public Circle() {}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+","+"name:"+name;
	}

}

package com.hwei.learning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.hwei.learning.model.Circle;

@Aspect
public class TransactionAspect {
	
	@Around("@annotation(com.hwei.learning.annotation.TransactionAnnotation)")
	public void loggingInsert(ProceedingJoinPoint proceedJoinPoint) {
		
		Object object=null;
		try {
			System.out.println(proceedJoinPoint.getTarget().getClass().getName()+" Before:"+parseArgument(proceedJoinPoint));
			object = proceedJoinPoint.proceed();
			System.out.println(proceedJoinPoint.getTarget().getClass().getName()+" After:"+object);
		} catch (Throwable e) {
			System.out.println("Throwing:"+e.getMessage());
		}
		
		System.out.println("Finally!");
				
	}
	
	@Around("execution(* queryCircleWithId(..))&&within(com.hwei.learning.dao.CircleResposity)")
	public Object loggingQuery(ProceedingJoinPoint proceedJoinPoint) {
		
		Object object=null;
		try {
			
			
			System.out.println("Before execute method:"+proceedJoinPoint.getTarget().getClass().getName()+"."+proceedJoinPoint.getSignature().getName()+"("+parseArgument(proceedJoinPoint)+")");
			object = proceedJoinPoint.proceed();
			System.out.println("After execute method:"+proceedJoinPoint.getTarget().getClass().getName()+"."+proceedJoinPoint.getSignature().getName()+", and the return result is :"+object);
		} catch (Throwable e) {
			System.out.println("Throwing:"+e.getMessage());
		}
		
		System.out.println("Finally!");
		
		return object;
		
	}
	
	
	
	
	private String parseArgument(JoinPoint joinPoint) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Arguments:(");
		Object[] points = joinPoint.getArgs();
		for(Object object:points) {
			builder.append(object.toString()+",");	
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();	
	}
	
	

}

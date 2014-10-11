package com.meteor.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Basic_aop {
	
	@Pointcut("execution(public * com.meteor.controller..*(..))")
	public void point_cut_area(){
		
	}

	@Around( value="point_cut_area()")
	public Object around(ProceedingJoinPoint joinpoint) throws Throwable{
		
		Object result = null;
		
		System.out.println("before 실행");

		HttpServletRequest request = null;
        HttpServletResponse response = null;
        
        for ( Object o : joinpoint.getArgs() ){ 
            if ( o instanceof HttpServletRequest ) {
                request = (HttpServletRequest)o;
                System.out.println( request );
            } 
            if ( o instanceof HttpServletResponse ) {
                response = (HttpServletResponse)o;
            } 
        }
		result = joinpoint.proceed();
	
		System.out.println("after 실행");
		
		return result;
		
	}
	
}

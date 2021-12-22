package com.jentrent.punchlist.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil{

	private static ApplicationContext appContext;

	static{

		try{
			appContext = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static Object getBean(String beannName){

		return appContext.getBean(beannName);
	}

	private SpringUtil(){

	}

}

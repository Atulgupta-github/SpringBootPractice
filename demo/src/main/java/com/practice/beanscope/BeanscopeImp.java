package com.practice.beanscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanscopeImp {
	
	@Autowired
	private Singletonbean bean1;
	
	@Autowired
	private Singletonbean bean2;
	
	
	@Autowired
	private Prototypebean bean3;
	
	@Autowired
	private Prototypebean bean4;
	
	public void  print() {
		
		
		try {
			System.out.println("=============================================");
			System.out.println(bean1==bean2);
			System.out.println(bean3==bean4);
			System.out.println("=============================================");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

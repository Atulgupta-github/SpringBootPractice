package com.practice;

import java.applet.AppletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.practice.beanscope.BeanscopeImp;
import com.practice.export.scheduler.ExportScheduler;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		 ConfigurableApplicationContext context = SpringApplication.run(PracticeApplication.class, args);
		
		 context.getBean(BeanscopeImp.class).print();
		 
		 context.getBean(ExportScheduler.class).init();
		
	}

}

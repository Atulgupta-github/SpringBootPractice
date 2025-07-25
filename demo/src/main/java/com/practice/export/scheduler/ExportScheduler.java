package com.practice.export.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.export.executer.ExportExecuter;

@Component
public class ExportScheduler {
	
	@Autowired
	private ExportExecuter exportExecuter;
	
	
	public void init() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		Runnable  task = ()->{
			exportExecuter.processData();
		};
		scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
	}
	

}

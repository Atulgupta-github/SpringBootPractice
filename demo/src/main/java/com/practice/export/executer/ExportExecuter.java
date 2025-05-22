package com.practice.export.executer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.export.sevice.UserInsertService;

@Component
public class ExportExecuter {
	
	@Autowired
	private UserInsertService userSInsertService;
	
	
	
	public void processData() {
		userSInsertService.inserUser();
		
		
	}

}

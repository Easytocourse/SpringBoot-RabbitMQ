package com.easytocourse.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easytocourse.model.ActionItems;
import com.easytocourse.service.Employeeservice;

@RestController
@RequestMapping("/Employee")
public class Employeecontroller {

	@Autowired
	Employeeservice empservice;
	
	
	@PostMapping("/addAction") 
	public ActionItems addemployee(@RequestBody ActionItems Item) {
		return empservice.saveaction(Item);
		
	}
	@GetMapping("/getActions")
	public List<ActionItems> getactions()
	{
		return empservice.getallactionitems();
		
	}
}

package com.test.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.db.service.DBService;

@Controller
public class DBController {
	
	@Autowired
	public DBService service;
	
	@GetMapping("/")
	public String login() {
		return "login";
		//return "index";
	}
	
	@GetMapping("/dbDashboard")
	public ModelAndView dbDashboard() {
		ModelAndView model = new ModelAndView("dbDashboard");
		model.addObject("connectionInfo", service.getConnectionInfo());
		return model;
	}
	
	@PostMapping("/execute")
	@ResponseBody 
	public String execute(@RequestParam("executeQry") String executeQry) {
		return service.executeQry(executeQry);
	}
	
	@PostMapping("/rollback")
	@ResponseBody
	public String rollBack() {
		return service.rollBack();
	}
	
	@PostMapping("/commit")
	@ResponseBody
	public String commit() {
		return service.commit();
	}

}

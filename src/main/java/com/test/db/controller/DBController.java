package com.test.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.db.service.DBService;

@Controller
public class DBController {
	
	@Autowired
	public DBService service;
	
	@GetMapping({"/","/login"})
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
	
	@PostMapping("/validate")
	public ModelAndView validate(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		if (username == null || username.trim().length() < 3) {
			model.addObject("error", "Username must be at least 3 characters");
			model.setViewName("login");
		}

		if (password == null || password.trim().length() < 8) {
			model.addObject("error", "Password must be at least 8 characters");
			model.setViewName("login");
		}

		// Simulate login check
		if ("admin".equals(username) && "Admin123".equals(password)) {
			// Call another method in the same controller after successful login
			 // Use flash attribute instead of addObject
	        redirectAttributes.addFlashAttribute("username", username);
			model.setViewName("redirect:/dbDashboard");
		} else {
			model.addObject("error", "Invalid username or password");
			model.setViewName("login");
		}
		return model;
	}



}

package com.ks.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ks.web.service.PersonService;

@Controller
@RequestMapping("/secure")
public class LoginController {

	@Autowired
	private PersonService personService;

	// For add and update person both
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successPage() {
		// return "redirect:/persons";
		return "success";

	}

	@RequestMapping("profile")
	public String geProfilePage() {
		return "profile";
	}

}

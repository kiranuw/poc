package com.ks.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ks.web.model.Person;
import com.ks.web.service.PersonService;

import flexjson.JSONSerializer;

@RestController
@RequestMapping("/rest")
public class CommonRestController {

	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value ="/person",headers="Accept=*/*",produces="application/json" )
	public String getPersonDetail(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "0") Integer id) {
		 JSONSerializer serializer = new JSONSerializer();
		Person p = personService.getPersonById(id);
		return serializer.serialize(p);
	}
}

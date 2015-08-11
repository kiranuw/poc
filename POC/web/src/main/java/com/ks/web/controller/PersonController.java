package com.ks.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.ks.web.model.Person;
import com.ks.web.service.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	@Autowired
	private VelocityEngine velocityEngine;
	private String viewName="personView";
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		
		
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		@SuppressWarnings("rawtypes")
		Map emailModal=new HashMap();
		emailModal.put("name", "Kiran Wayal");
		String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "velocity/testEmail.vm", "UTF-8", emailModal);
             System.out.println(text);
		//return "person";
		return viewName;
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        String url="http://localhost:8181/ERM/rest/person?id="+id;  
        RestTemplate restTemplate = new RestTemplate();
		String users=restTemplate.getForObject(url, String.class);
		System.out.println("Object from Rest : "+users);
		return viewName;
    }
    
    
	
}


package com.ks.web.schedular;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ks.web.service.PersonService;

@Component
public class PicupCleanSchedular {
	 

	  @Autowired 
	  private PersonService personService;
	    
	   //@Scheduled(cron="*/5 * * * * ?")
	    public void demoServiceMethod()
	    {
	        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
	        try {
	        	//personService.cleanCustomerOrders();
	        	//Person p =personService.getPersonById(1);
	        	//System.out.println(p.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
}

package com.ks.web.dao;

import com.ks.web.model.AppUser;


public interface UserDao {
	
	AppUser findByUserName(String username);
}

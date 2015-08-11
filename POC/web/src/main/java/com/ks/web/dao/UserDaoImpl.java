package com.ks.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ks.web.model.AppUser;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
 
	@SuppressWarnings("unchecked")
	public AppUser findByUserName(String username) {
 
		List<AppUser> users = new ArrayList<AppUser>();
 
		users = sessionFactory.getCurrentSession()
			.createQuery("from AppUser where username=?")
			.setParameter(0, username)
			.list();
 
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
 
	}
}

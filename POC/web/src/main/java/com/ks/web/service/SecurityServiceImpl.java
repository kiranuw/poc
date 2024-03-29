package com.ks.web.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ks.web.dao.UserDao;
import com.ks.web.model.AppUser;
import com.ks.web.model.UserRole;


@Service("userDetailsService")
@Transactional
public class SecurityServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		AppUser user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = 
                                      buildUserAuthority(user.getUserRole());
 
		return buildUserForAuthentication(user, authorities);
		
		
		
	}

	
	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(AppUser user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
}

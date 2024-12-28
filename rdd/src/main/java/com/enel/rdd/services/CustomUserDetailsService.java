package com.enel.rdd.services;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enel.rdd.storedProcedure.LoadUsersByUsernameStoredProcedure;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		InitialContext initi = null;
		try {
			initi = new InitialContext();
		} catch (NamingException e2) {
			e2.printStackTrace();
		}
		DataSource dataSourceBTN = null;
		try {
			dataSourceBTN = (DataSource) initi.lookup("java:/CHILECTRA_BTN");
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		LoadUsersByUsernameStoredProcedure loadUsers = new LoadUsersByUsernameStoredProcedure(dataSourceBTN);
		
		List<UserDetails> users = loadUsers.execute(username);		
		
		return users.get(0);
	}

}

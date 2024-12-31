package com.enel.rdd.login.services;

import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enel.rdd.login.storedProcedure.LoadAuthorityByUsernameStoredProcedure;
import com.enel.rdd.login.storedProcedure.LoadUsersByUsernameStoredProcedure;



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
		
		LoadUsersByUsernameStoredProcedure loadUsers = null;
		try {
			loadUsers = new LoadUsersByUsernameStoredProcedure(dataSourceBTN.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserDetails user = loadUsers.execute(username);
		
		LoadAuthorityByUsernameStoredProcedure loadAuthority = null;
		try {
			loadAuthority = new LoadAuthorityByUsernameStoredProcedure(dataSourceBTN.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<GrantedAuthority> grantedAuthoritys = loadAuthority.execute(username);
		
		return createUserDetails(username, user, grantedAuthoritys);
	}
	
	public UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {

		return new User(userFromUserQuery.getUsername(), userFromUserQuery.getPassword(), 
                       userFromUserQuery.isEnabled(),
		       userFromUserQuery.isAccountNonExpired(), 
                       userFromUserQuery.isCredentialsNonExpired(),
			userFromUserQuery.isAccountNonLocked(), combinedAuthorities);
	}

}

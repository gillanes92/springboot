package com.enel.rdd.login.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.enel.rdd.login.storedProcedure.mapper.UserMapper;



public class LoadUsersByUsernameStoredProcedure {
	
	private CallableStatement callableStatement = null;
	private Connection conn = null;

	public LoadUsersByUsernameStoredProcedure(Connection conn) throws SQLException{
		
		this.conn = conn;
		this.conn.setAutoCommit(false);
		callableStatement = this.conn.prepareCall("{call ACSPSEC.SP_ACP_LOAD_USER_BY_USERNAME(?,?,?) }");
 
	}
	
	public UserDetails execute(String username) {
		
		UserDetails user = null;
		
		try {
			
			callableStatement.setString(1, username);
			
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.OTHER);

			callableStatement.execute();
						
			ResultSet rs = (ResultSet) callableStatement.getObject(3);
			
			UserMapper userMapper = new UserMapper();
			
			while(rs.next()) {
				
				userMapper.setUsername(rs.getString("username"));
				userMapper.setPassword(rs.getString("password"));
				userMapper.setEnabled(rs.getBoolean("enabled"));
				userMapper.setAccountNonExpired(rs.getBoolean("accountNonExpired"));
				userMapper.setCredentialsNonExpired(rs.getBoolean("credentialsNonExpired"));
				userMapper.setAccountNonLocked(rs.getBoolean("accountNonLocked"));
			}
			
			user = new User(userMapper.getUsername(), "{noop}"+" ", userMapper.isEnabled(),
					userMapper.isAccountNonExpired(), userMapper.isCredentialsNonExpired(), userMapper.isAccountNonLocked(), AuthorityUtils.NO_AUTHORITIES);
						
			callableStatement.close();
			this.conn.close();
 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				if(callableStatement != null) 
					callableStatement.close();
			
				if(this.conn != null)
					this.conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return user;
	}

}

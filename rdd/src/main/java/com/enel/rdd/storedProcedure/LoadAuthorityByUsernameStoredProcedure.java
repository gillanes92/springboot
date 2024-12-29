package com.enel.rdd.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class LoadAuthorityByUsernameStoredProcedure {
	
	private CallableStatement callableStatement = null;
	private Connection conn = null;

	public LoadAuthorityByUsernameStoredProcedure(Connection conn) throws SQLException{
		
		this.conn = conn;
		this.conn.setAutoCommit(false);
		callableStatement = this.conn.prepareCall("{call ACSPSEC.sp_acp_load_authority_by_username(?,?,?) }");
 
	}
	
	public List<GrantedAuthority> execute(String username) {
		
		List<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
		
		try {
			
			callableStatement.setString(1, username);
			
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.OTHER);

			callableStatement.execute();
						
			ResultSet rs = (ResultSet) callableStatement.getObject(3);
			
			while(rs.next()) {
				
				grantedAuthoritys.add(new SimpleGrantedAuthority(rs.getString("authority")));
			}
						
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
		
		return grantedAuthoritys;
	}

}

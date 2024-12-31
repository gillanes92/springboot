package com.enel.rdd.dashboard.controllers;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.enel.rdd.dashboard.storedProcedure.UsuarioObtenerStoredProcedure;
import com.enel.rdd.utils.mappers.Usuario;


@Controller
public class DashboardController {
	
	@GetMapping("/dashboard")
	public String home(Model model) {
		
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
		
		
		SecurityContext a = SecurityContextHolder.getContext();
		
		UsuarioObtenerStoredProcedure usuario = new UsuarioObtenerStoredProcedure(dataSourceBTN);
		
		Usuario user = null;
		try {
			user = usuario.execute(a.getAuthentication().getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer companyId = user.getEmpresa().getId();
		
		if (companyId == 1){
			return "dashboardChile";
		}else {
			return "dashboardChile";
		}
		
	}
	
}

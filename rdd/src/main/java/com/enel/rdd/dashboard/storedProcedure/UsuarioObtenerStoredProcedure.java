package com.enel.rdd.dashboard.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.enel.rdd.dashboard.storedProcedure.mapper.UsuarioFullMapper;
import com.enel.rdd.utils.mappers.Rol;
import com.enel.rdd.utils.mappers.Usuario;

public class UsuarioObtenerStoredProcedure extends StoredProcedure {
	private static final String SPROC_NAME = "ACSPSEC.SP_ACP_SEL_ACC_USER";
	private static final String nombre_in_param	= "nombre_in";
	
	private static final String ncod_salida_out_param	= "ncod_salida_out";
	private static final String vmsg_salida_out_param	= "vmsg_salida_out";
	private static final String cur_usuario_out	= "cur_usuario_out";
	
	public UsuarioObtenerStoredProcedure(DataSource dataSource) {
		super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(nombre_in_param, Types.VARCHAR));
        
        declareParameter(new SqlOutParameter(ncod_salida_out_param, Types.VARCHAR));
        declareParameter(new SqlOutParameter(vmsg_salida_out_param, Types.VARCHAR));
        declareParameter(new SqlOutParameter(cur_usuario_out, Types.OTHER, new UsuarioFullMapper() ));
        
        compile();
    }
	
	public Usuario execute(String usuario) throws SQLException {
		Map<String, Object> inputs = new HashMap<String, Object>();
        inputs.put(nombre_in_param, usuario);
       
        Map<String,Object> mapa = super.execute(inputs);

		
		return armarUsuario((List<Usuario>)mapa.get(cur_usuario_out));

    }
	
	
	private Usuario armarUsuario(List<Usuario> usuarios){
		Usuario usuario = new Usuario();
 
		usuario = usuarios.get(0);
		List<Rol> roles = new ArrayList<Rol>();
		for(Usuario u : usuarios){
			roles.add(u.getRolActual());
		}
		usuario.setRoles(roles);
		return usuario;
	}
	
	
}

package com.enel.rdd.storedProcedure;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.security.core.userdetails.UserDetails;

import com.enel.rdd.storedProcedure.mapper.UserDetailsMapper;

public class LoadUsersByUsernameStoredProcedure extends StoredProcedure {
	private static final String SPROC_NAME = "ACSPSEC.SP_ACP_LOAD_USER_BY_USERNAME";
	private static final String nombre_in_param = "nombre_in";

	private static final String ncod_salida_out_param = "ncod_salida_out";
	private static final String vmsg_salida_out_param = "vmsg_salida_out";
	private static final String cur_usuario_out = "cur_usuario_out";

	public LoadUsersByUsernameStoredProcedure(DataSource dataSource) {
		super(dataSource, SPROC_NAME);
		declareParameter(new SqlParameter(nombre_in_param, Types.VARCHAR));

		declareParameter(new SqlOutParameter(ncod_salida_out_param, Types.VARCHAR));
		declareParameter(new SqlOutParameter(vmsg_salida_out_param, Types.VARCHAR));
		declareParameter(new SqlOutParameter(cur_usuario_out, Types.OTHER, new UserDetailsMapper()));

		compile();
	}

	public List<UserDetails> execute(String username) {
				
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put(nombre_in_param, username);

		Map<String,Object> mapa = super.execute(inputs);
		
		List<UserDetails> list = null;
		if(mapa.get(cur_usuario_out)!=null){
			list = (List<UserDetails>) mapa.get(cur_usuario_out);
		}
		
		return list;
	}

}

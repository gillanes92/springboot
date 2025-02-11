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
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.enel.rdd.dashboard.storedProcedure.mapper.UsuarioFullMapper;
import com.enel.rdd.utils.mappers.Empresa;
import com.enel.rdd.utils.mappers.Rol;
import com.enel.rdd.utils.mappers.Usuario;

public class UsuarioObtenerStoredProcedure extends StoredProcedure {

	private CallableStatement callableStatement = null;
	private Connection conn = null;

	public UsuarioObtenerStoredProcedure(Connection conn) throws SQLException {

		this.conn = conn;
		this.conn.setAutoCommit(false);
		callableStatement = this.conn.prepareCall("{call ACSPSEC.SP_ACP_SEL_ACC_USER(?,?,?) }");

	}

	public Usuario execute(String usuario) throws SQLException {
		
		Usuario user = new Usuario();
		
		try {

			callableStatement.setString(1, usuario);

			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.OTHER);

			callableStatement.execute();

			ResultSet rs = (ResultSet) callableStatement.getObject(3);

			while (rs.next()) {
				user.setId(rs.getInt("ID_USUARIO"));
				user.setUsuario(rs.getString("USUARIO"));
				user.setNombre(rs.getString("NOMBRE"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
				user.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
				user.setCorreo(rs.getString("CORREO"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setSuperUsuario(rs.getBoolean("SUPERUSUARIO"));
				user.setEstado(rs.getBoolean("ESTADO"));
				user.setFechaActualizacion(rs.getDate("FECHA_ACTUALIZACION"));
				user.setFechaActualizacionPassword(rs.getDate("FECHA_ACTUALIZACION_PASSWORD"));
				Empresa empresa = new Empresa();
				empresa.setId(rs.getInt("ID_EMPRESA"));
				empresa.setNombre(rs.getString("NOMBRE_EMPRESA"));
				user.setEmpresa(empresa);
				
				Rol rol = new Rol();
				rol.setId(rs.getInt("ID_ROL"));
				rol.setNombre(rs.getString("NOMBRE_ROL"));
				rol.setEmpresa(empresa);
				user.setRolActual(rol);
			}

			callableStatement.close();
			this.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {

				if (callableStatement != null)
					callableStatement.close();

				if (this.conn != null)
					this.conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return user;

	}

	private Usuario armarUsuario(List<Usuario> usuarios) {
		Usuario usuario = new Usuario();

		usuario = usuarios.get(0);
		List<Rol> roles = new ArrayList<Rol>();
		for (Usuario u : usuarios) {
			roles.add(u.getRolActual());
		}
		usuario.setRoles(roles);
		return usuario;
	}

}

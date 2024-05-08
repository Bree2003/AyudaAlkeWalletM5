package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.DBConexion;
import modelo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{
	
	private String sql = "";
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private Usuario usuario = null;

	@Override
	public int guardar(Usuario usuario) {
		int row = 0;
		
		sql = "insert into usuarios(nombre,apellido,correo,contrasena,saldo) values (?,?,?,?,?)";
		
		//obtenemos la conexion
		con = DBConexion.getConexion();
		try {
			//vamos a indicar nuestra sentencia sql
			pstm = con.prepareStatement(sql);
			//setear los parametros
			pstm.setString(1, usuario.getNombre());
			pstm.setString(2, usuario.getApellido());
			pstm.setString(3, usuario.getCorreo());
			pstm.setString(4, usuario.getContrasena());
			pstm.setDouble(5, usuario.getSaldo());
			// le indicamos que ejecute la query en la base de datos
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}

	@Override
	public int actualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Usuario> obtenerTodos() {
		List<Usuario> lista = new ArrayList<>();
		Statement stm;
		sql = "select * from usuarios";
		con = DBConexion.getConexion();
		try {
			stm = con.createStatement();
			//resultset nos trae los datos (se utiliza con select)
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
				lista.add(usuario);
			}
			stm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

}
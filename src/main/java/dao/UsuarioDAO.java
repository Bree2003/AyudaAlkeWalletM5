package dao;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {

	public int guardar(Usuario usuario);
	public int actualizar(Usuario usuario);
	public List<Usuario> obtenerTodos();
	//public int eliminar(int idUsuario);

}

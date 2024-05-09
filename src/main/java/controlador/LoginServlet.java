package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.DBConexion;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;     
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// con requestdispatcher se hace una redireccion interna
				//si lo enviamos con la ruta /registarse seguira mostrando /registrase pero con la vista cambiada
				RequestDispatcher dispatcher = null;
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// traernos los parametros
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		// creamos sesion para el usuario, colocamos true para que se cree una si es que no encuentra una
		HttpSession session = request.getSession(true);
		String sql = "select * from usuarios where correo = ? and contrasena = ?";
		
		try {
			Connection con = DBConexion.getConexion();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, correo);
			pstm.setString(2, contrasena);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
				//request.setAttribute("usuario", usuario);
				//INFO ESTO ES LO QUE NOS DA EL USUARIO ESTATICO
				//TODO HACER EL USUARIO DINAMICO
				session.setAttribute("usuario", usuario);
				// le estamos diciendo que rediriga a esa pestaña
				dispatcher = request.getRequestDispatcher("home.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

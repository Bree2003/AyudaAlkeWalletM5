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

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;  
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// con requestdispatcher se hace una redireccion interna
				//si lo enviamos con la ruta /registarse seguira mostrando /registrase pero con la vista cambiada
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// traernos los parametros
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		
		
		//nos traemos el usuario
		Usuario usuario = usuarioDAO.obtenerUsuario(correo, contrasena);
		
		if(usuario != null) {
			// creamos sesion para el usuario, colocamos true para que se cree una si es que no encuentra una
			HttpSession session = request.getSession(true);
			// nos guardamos el id del usuario
			session.setAttribute("id", usuario.getId());
			response.sendRedirect("home");
		} else {	
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		

}
}
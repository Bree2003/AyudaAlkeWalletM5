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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// nos traemos al usuario guardado en la sesion
		HttpSession session = request.getSession(false);
		// colocamos false para que no nos cree una si es que no existe
		
		// si no existe sesion
		if(session == null) {
			//que nos redireccione al login
			response.sendRedirect("login.jsp");
		} else {
			// y si es que existe al home
			
			// nos traemos el id del usuario
			int id = (Integer) session.getAttribute("id");
			
			Usuario usuario = usuarioDAO.obtenerUsuarioPorID(id);
			session.setAttribute("usuario", usuario);
			dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
	}


	
       

}

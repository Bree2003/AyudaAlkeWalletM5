package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;

@WebServlet("/registrarse")
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario usuario = null;
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		RequestDispatcher dispatcher = null;
		
		//obtener parametros
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		int operacion;
		
		try {
			usuario = new Usuario(nombre,apellido,correo,contrasena,0.0);
			operacion = usuarioDAO.guardar(usuario);
			if(operacion > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				request.setAttribute("status", "failed");
			}
		dispatcher.forward(request, response);
		} catch (Exception e)  {
			e.printStackTrace();
		}
		
	}

}

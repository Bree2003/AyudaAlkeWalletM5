package controlador;

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

@WebServlet("/operacion")
public class OperacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	Usuario usuario = null;
	int exitoso = 0;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// obtenemos parametros de la ruta
				String operacion = request.getParameter("operacion");
				Double monto = Double.parseDouble(request.getParameter("monto"));
				
				// obtener sesion
				HttpSession session = request.getSession(false);
				int id = (int) session.getAttribute("id");
				
				// obtener usuario
				usuario = (Usuario) session.getAttribute("usuario");
		
				//saber si es depositar o retirar
				if(operacion.equals("depositar")) {
					exitoso = usuarioDAO.depositar(monto, id);
					if(exitoso > 0) {
						session.setAttribute("status", "success");
						response.sendRedirect("home");
					} else {
						session.setAttribute("status", "failed");
						response.sendRedirect("home");
					}
				} else if (operacion.equals("retirar")) {
					if(usuario.getSaldo() >= monto) {
						exitoso = usuarioDAO.retirar(monto, id);
						if(exitoso > 0) {
							session.setAttribute("status", "success");
							response.sendRedirect("home");
						} else {
							session.setAttribute("status", "failed");
							response.sendRedirect("home");
						}
					} else {
						session.setAttribute("status", "failed");
						response.sendRedirect("home");
					}
					
				}
		
		
		}
	

}

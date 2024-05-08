package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;

@WebServlet("/lista")
public class Lista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	RequestDispatcher dispatcher = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Usuario> lista = new ArrayList<>();
		lista = usuarioDAO.obtenerTodos();
		request.setAttribute("lista", lista);
		dispatcher = request.getRequestDispatcher("lista.jsp");
		dispatcher.forward(request, response);
		}



}

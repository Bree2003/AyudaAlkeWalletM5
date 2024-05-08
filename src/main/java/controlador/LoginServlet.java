package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.DBConexion;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// traernos los parametros
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		HttpSession session = request.getSession();
		String sql = "select * from usuarios where correo = ? and contrasena = ?";
		
		try {
			Connection con = DBConexion.getConexion();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, correo);
			pstm.setString(2, contrasena);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				dispatcher = request.getRequestDispatcher("home.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

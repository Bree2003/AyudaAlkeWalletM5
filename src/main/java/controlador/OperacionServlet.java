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
import java.sql.SQLException;

import conexion.DBConexion;

@WebServlet("/operacion")
public class OperacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// con requestdispatcher se hace una redireccion interna
		//si lo enviamos con la ruta /registarse seguira mostrando /registrase pero con la vista cambiada
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// obtenemos parametros de la ruta
		String operacion = request.getParameter("operacion");
		Double monto = Double.parseDouble(request.getParameter("monto"));
		System.out.println(monto);
		
		// obtener sesion
		HttpSession session = request.getSession(false);
		// nos traemos nuestro usuario
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		System.out.println(usuario.getId());
		
		//hacemos la solicitud a la bd
		try {
			// establecemos conexion a la base de datos
			Connection con = DBConexion.getConexion();
			
			//saber si es depositar o retirar
			if(operacion.equals("depositar")) {
				//indicamos consulta dinamica sql
				String sql = "UPDATE usuarios SET saldo = ? WHERE id = ?";
				//statement -> consultas estaticas
				//preparedstatement -> consulta dinamica, le debemos pasar los parametros
				// estos son representados con un ?
				PreparedStatement pstm = con.prepareStatement(sql);
				//setear parametros en la query
				monto = usuario.getSaldo() + monto;
				System.out.println(monto);
				pstm.setDouble(1, monto);
				pstm.setInt(2, usuario.getId());
				pstm.executeUpdate();
			} else if(operacion.equals("retirar")) {
				//indicamos consulta dinamica sql
				String sql = "UPDATE usuarios SET saldo = ? WHERE id = ?";
				//statement -> consultas estaticas
				//preparedstatement -> consulta dinamica, le debemos pasar los parametros
				// estos son representados con un ?
				PreparedStatement pstm = con.prepareStatement(sql);
				//setear parametros en la query
				//TODO CAMBIAR A USUARIO DINAMICO
				monto = usuario.getSaldo() - monto;
				System.out.println(monto);
				pstm.setDouble(1, monto);
				pstm.setInt(2, usuario.getId());
				pstm.executeUpdate();
			}
			response.sendRedirect("home.jsp");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}

}

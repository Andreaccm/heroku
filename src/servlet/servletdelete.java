package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.loginB;
import helpers.DB;

@WebServlet("/servletdelete")
public class servletdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;
	
	public void init() {
		db = new DB();
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		loginB lb = new loginB();
		System.out.println(loginB.email);
	
			try {
				if(db.delete(lb)){
					res.sendRedirect("login.html");
				}else {
					res.sendRedirect("login.html");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}
}

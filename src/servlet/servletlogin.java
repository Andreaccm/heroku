package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.loginB;
import controllers.perfiluser;
import helpers.DB;

@WebServlet("/servletlogin")
public class servletlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;
	
	public void init() {
		db = new DB();
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		loginB lb = new loginB();
		lb.setEmail(email);
		perfiluser.email = email;
		lb.setPwd(pwd);
		
			try {
				if(db.login(lb)) {
					HttpSession session = req.getSession();
					session.setAttribute("email",email);
					res.sendRedirect("account.html");
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

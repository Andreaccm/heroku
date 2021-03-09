package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.DB;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	DB db = new DB();
		
		String name = req.getParameter("name");
		String user = req.getParameter("user"); 
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String date = req.getParameter("date");
		String pwd = req.getParameter("pwd");
		
			db.register(name, user, email, phone, date, pwd);
			res.sendRedirect("login.html");
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.perfiluser;
import helpers.DB;

@WebServlet("/servletupdate")
public class servletupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private DB db;
	
    public servletupdate() {
        super();
    }
    public void init() {
		db = new DB();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: "+perfiluser.name).append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(req, res);
	}

}

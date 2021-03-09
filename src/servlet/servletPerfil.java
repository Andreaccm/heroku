package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.loginB;
import controllers.perfiluser;
import helpers.DB;

@WebServlet("/servletPerfil")
public class servletPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private DB db;
    public servletPerfil() {
        super();

    }
    public void init() {
		db = new DB();
	}
    
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    perfiluser p = new perfiluser();
    loginB lb = new loginB();
	System.out.println(loginB.email);

		try {
			if(db.perfil(p, lb)){
				res.sendRedirect("login.html");
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("email",loginB.email);
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
 
    
    
    
    	
    }

}

package app.one.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.one.dao.DaoActions;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutServlet")
public class SignoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 response.setContentType("text/html");  
         PrintWriter out=response.getWriter();  
         HttpSession session=request.getSession();  
         String username = (String)request.getSession().getAttribute("name");
         System.out.println("Username is - "+request.getSession().getAttribute("name"));
         DaoActions.clearToken(username);
         session.invalidate();  
           
         //out.print("You are successfully logged out!");     
         request.getRequestDispatcher("/login.jsp").include(request, response);  
           
        
           
         out.close();   
		 
	 
	 }

}

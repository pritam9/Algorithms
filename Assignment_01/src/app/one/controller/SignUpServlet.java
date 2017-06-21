package app.one.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.one.dao.DaoActions;
import app.one.model.Products;
import app.one.model.Response;
import app.one.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setPassword(request.getParameter("password"));
		user.setUsername(request.getParameter("username"));
		System.out.println(request.getParameter("name"));
		user.setName(request.getParameter("name"));
		Response my_response = new Response();
		RequestDispatcher rd = null;
		
		if ( DaoActions.isNewUser(user))
		{
			System.out.println("SignUp Success!!");
			my_response.setStatus(Response.SUCCESS);
			my_response.setMessage("Signup Successfull!!");
			my_response.setData(user);
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			/*rd=request.getRequestDispatcher("/login.jsp");  
            rd.include(request, response);*/
		}
		else
		{	
			System.out.println("SignUp Failure!!");
			my_response.setStatus(Response.ERROR);
			my_response.setMessage("Signup Failed!!");
			my_response.setData("Username not available!!");
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			//System.out.println("Success!!");
			/*request.setAttribute("signUpError", "Invalid Username Passoword!!");
			rd=request.getRequestDispatcher("/home.jsp");
			rd.include(request, response);*/
		}
	}

}

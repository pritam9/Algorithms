package app.one.controller;

import java.io.IOException;

import com.auth0.jwt.JWTSigner;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JWindow;
import org.jose4j.jwe.JsonWebEncryption;
import java.security.Key;
import java.util.HashMap;

import app.one.dao.DaoActions;
import app.one.model.Response;
import app.one.model.User;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		Response my_response = new Response();
		RequestDispatcher rd = null;
		if ( DaoActions.isValidUser(user))
		{
			System.out.println("Success!!");
			
			//JsonWebEncryption encryption = new JsonWebEncryption();
			//encryption.
				
			final String issuer = "https://mydomain.com/";
			final String secret = "{{a secret used for signing}}";

			final long iat = System.currentTimeMillis() / 1000l; // issued at claim 
			final long exp = iat + 60L; // expires claim. In this case the token expires in 60 seconds

			final JWTSigner signer = new JWTSigner(secret);
			final HashMap<String, Object> claims = new HashMap<String, Object>();
			claims.put("iss", issuer);
			claims.put("exp", exp);
			claims.put("iat", iat);
			claims.put("userid", user.getUsername());
			final String jwt = signer.sign(claims);
			System.out.println(jwt);
					
			
			my_response.setStatus(Response.SUCCESS);
			my_response.setMessage("Login Successfull!!");
			String token = DaoActions.getToken(user);
			my_response.setData(jwt);
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			/*HttpSession session = request.getSession(false);
			if(session!=null)
				  {
				session.setAttribute("name", user.getUsername());
				session.setAttribute("token", token);
				  }*/
			/*rd=request.getRequestDispatcher("/todo.jsp");  
            rd.include(request, response);*/
		}
		else
		{	
			System.out.println("Failure!!");
			my_response.setStatus(Response.ERROR);
			my_response.setMessage("Login Failed!!");
			my_response.setData("Login Failed!!");
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			//System.out.println("Success!!");
			/*request.setAttribute("Error", "Invalid Username Passoword!!");
			rd=request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);*/
		}
		
	}

}

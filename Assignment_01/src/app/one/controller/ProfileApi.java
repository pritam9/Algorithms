package app.one.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.google.gson.Gson;
//import com.sun.javafx.collections.MappingChange.Map;

import app.one.dao.DaoActions;
import app.one.model.Response;
import app.one.model.User;

/**
 * Servlet implementation class Test
 */

public class ProfileApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileApi() {
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
		//doGet(request, response);
		
		String token = (request.getParameter("token"));
		//user.setUsername(request.getParameter("username"));
		
		final String secret = "{{a secret used for signing}}";
		try {
		    final JWTVerifier verifier = new JWTVerifier(secret);
			final Map<String,Object> claims= verifier.verify(token);
		    System.out.println("Jwt Username is -"+ claims.get("userid"));
		    
		    
			RequestDispatcher rd = null;
			Response my_response = new Response();
			System.out.println("Profile API Called");
			long iat = System.currentTimeMillis() / 1000l;
			Integer exp = (Integer) claims.get("exp");
			if (exp >iat)
			{
				User user = DaoActions.getMyDetails((String) claims.get("userid"));
				if ( user != null)
				{
					System.out.println("Success!!");
					my_response.setStatus(Response.SUCCESS);
					my_response.setMessage("Login Successfull!!");
					my_response.setData(user);
					//session.setAttribute("token", user.getToken());
					System.err.println("Full Name is - ");
					String json_response = new Gson().toJson(my_response);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json_response);
					/*HttpSession session = request.getSession(false);
					if(session!=null)
						  session.setAttribute("name", user.getUsername());
					rd=request.getRequestDispatcher("/todo.jsp");  
		            rd.include(request, response);*/
				}
				else
				{
					System.out.println("Profile API - Failure!!");
					my_response.setStatus(Response.ERROR);
					my_response.setMessage("No data Available!!");
					my_response.setData("Login Failed!!");
					String json_response = new Gson().toJson(my_response);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json_response);
				}
			}else{
				System.out.println("Profile API - Failure!!");
				my_response.setStatus(Response.ERROR);
				my_response.setMessage("TOken Expired!!");
				my_response.setData("API call Failed!!");
				String json_response = new Gson().toJson(my_response);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json_response);
			}
			
			
		} catch (Exception e) {
		    // Invalid Token
			e.printStackTrace();
			Response my_response = new Response();
			System.out.println("Profile API - Failure!!");
			my_response.setStatus(Response.ERROR);
			my_response.setMessage("Token Expired/Invalid!!");
			my_response.setData("Exception while calling API");
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
		} 
		/*
		Response my_response = new Response();
		System.out.println("Profile API Called");
		
		/*HttpSession session = request.getSession();
		String session_token = (String) session.getAttribute("token");
		if (session_token != null && !token.equals(session_token))
			token = session_token;
		System.out.println(session.getAttribute("token"));
		User user = DaoActions.getMyDetails(token);
		RequestDispatcher rd = null;
		if ( user != null)
		{
			System.out.println("Success!!");
			my_response.setStatus(Response.SUCCESS);
			my_response.setMessage("Login Successfull!!");
			my_response.setData(user);
			session.setAttribute("token", user.getToken());
			System.err.println("Full Name is - ");
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			/*HttpSession session = request.getSession(false);
			if(session!=null)
				  session.setAttribute("name", user.getUsername());
			rd=request.getRequestDispatcher("/todo.jsp");  
            rd.include(request, response);
		}
		else
		{	
			System.out.println("Profile API - Failure!!");
			my_response.setStatus(Response.ERROR);
			my_response.setMessage("Token Expired/Invalid!!");
			my_response.setData("Login Failed!!");
			String json_response = new Gson().toJson(my_response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			//System.out.println("Success!!");
			/*request.setAttribute("Error", "Invalid Username Passoword!!");
			rd=request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);
		}*/
	
	}

}

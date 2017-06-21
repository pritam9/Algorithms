package app.one.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.one.dao.DaoActions;
import app.one.model.MockUsers;
import app.one.model.Products;
import app.one.model.Response;
import app.one.util.PushNotification;

/**
 * Servlet implementation class GetMockUsers
 */

public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside GetUsers!!");
		//Products product = new Products();
		//user.setPassword(request.getParameter("password"));
		//user.setUsername(request.getParameter("username"));
		String order_by = request.getParameter("order_by");
		String asc_order = request.getParameter("is_asc");
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		System.out.println(request.getParameter("is_asc"));
		//user.setName(request.getParameter("name"));
		Response my_response = new Response();
		RequestDispatcher rd = null;
		List<MockUsers> userList = DaoActions.getUserList(page,asc_order,order_by);
		if ( userList.size()>0)
		{
			System.out.println("Got Users List ");
			/*my_response.setStatus(Response.SUCCESS);
			my_response.setMessage("Signup Successfull!!");
			my_response.setData(user);*/
			MockUsers user = userList.get(0);
			//PushNotification.send(sender_token.trim(), product.getDiscount()+"% discount on "+product.getName());
			Map<String, List<MockUsers>> json_map = new HashMap<String, List<MockUsers>>();
			json_map.put("users",userList);
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			/*rd=request.getRequestDispatcher("/login.jsp");  
            rd.include(request, response);*/
		}
		else
		{
			Map<String, List<MockUsers>> json_map = new HashMap<String, List<MockUsers>>();
			json_map.put("results",userList);
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

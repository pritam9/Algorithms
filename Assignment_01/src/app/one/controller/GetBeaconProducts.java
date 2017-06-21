package app.one.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import app.one.dao.DaoActions;
import app.one.model.Products;
import app.one.model.Response;
import app.one.model.User;
import app.one.util.PushNotification;

/**
 * Servlet implementation class BeaconProducts
 */

public class GetBeaconProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseReference firebase;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBeaconProducts() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) {
    	String credential = config.getInitParameter("credential");
        String databaseUrl = config.getInitParameter("databaseUrl");
        
        FirebaseOptions options = new FirebaseOptions.Builder()
        	      .setServiceAccount(config.getServletContext().getResourceAsStream(credential))
        	      .setDatabaseUrl(databaseUrl)
        	      .build();
        	    FirebaseApp.initializeApp(options);
        	    
        	    firebase = FirebaseDatabase.getInstance().getReference();
        	   
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
		/*URL f = GetBeaconProducts.class.getClassLoader().getResource("/Users/Pritam/Documents/workspace/Assignment_01/src/aap/one/files/BeaconServer");
		System.out.println("Url used is - "+f);
		File file = new File("/Users/Pritam/Documents/workspace/Assignment_01/src/aap/one/files/BeaconServer.json");
		//Firebase connection from Server
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/JSON_FILE_NAME"))
				  .setDatabaseUrl("https://notification-center-cfd32.firebaseio.com/")
				  .build();
				FirebaseApp.initializeApp(options);*/
		
		System.out.println("Inside BeaconServlete!!");
		//Products product = new Products();
		//user.setPassword(request.getParameter("password"));
		//user.setUsername(request.getParameter("username"));
		String region = request.getParameter("region");
		String sender_token = request.getParameter("token");
		System.out.println(request.getParameter("region"));
		//user.setName(request.getParameter("name"));
		Response my_response = new Response();
		RequestDispatcher rd = null;
		List<Products> productList = DaoActions.getProductList(region);
		if ( productList.size()>0)
		{
			System.out.println("Got Product List ");
			/*my_response.setStatus(Response.SUCCESS);
			my_response.setMessage("Signup Successfull!!");
			my_response.setData(user);*/
			Products product = productList.get(0);
			PushNotification.send(sender_token.trim(), product.getDiscount()+"% discount on "+product.getName());
			Map<String, List<Products>> json_map = new HashMap<String, List<Products>>();
			json_map.put("results",productList);
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			/*rd=request.getRequestDispatcher("/login.jsp");  
            rd.include(request, response);*/
		}
		else
		{
			Map<String, List<Products>> json_map = new HashMap<String, List<Products>>();
			json_map.put("results",productList);
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
		}
	}

}

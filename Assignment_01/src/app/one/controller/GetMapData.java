package app.one.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import app.one.dao.DaoActions;
import app.one.model.MapData;
import app.one.model.Products;
import app.one.model.Response;
import app.one.util.PushNotification;

public class GetMapData extends HttpServlet{
	
	public GetMapData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*@Override
    public void init(ServletConfig config) {
    	String credential = config.getInitParameter("credential");
        String databaseUrl = config.getInitParameter("databaseUrl");
        
        FirebaseOptions options = new FirebaseOptions.Builder()
        	      .setServiceAccount(config.getServletContext().getResourceAsStream(credential))
        	      .setDatabaseUrl(databaseUrl)
        	      .build();
        	    FirebaseApp.initializeApp(options);
        	    
        	    firebase = FirebaseDatabase.getInstance().getReference();
        	   
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//doPost(request, response);
		
		RequestDispatcher rd = null;
		if(request.getRequestURL().toString().contains("getMapData")){
		List<MapData> mapDataList = DaoActions.getMapDataList();
		if ( mapDataList.size()>0)
		{
			System.out.println("Got Product List ");
			/*my_response.setStatus(Response.SUCCESS);
			my_response.setMessage("Signup Successfull!!");
			my_response.setData(user);*/
			//Products product = productList.get(0);
			//PushNotification.send(sender_token.trim(), product.getDiscount()+"% discount on "+product.getName());
			Map<String, List<MapData>> json_map = new HashMap<String, List<MapData>>();
			json_map.put("data",mapDataList);
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
			/*rd=request.getRequestDispatcher("/login.jsp");  
            rd.include(request, response);*/
		}
		else
		{
			Map<String, List<MapData>> json_map = new HashMap<String, List<MapData>>();
			json_map.put("results",mapDataList);
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
		}
		}else{
			Map<String, String> json_map = new HashMap<String, String>();
			json_map.put("status","Error");
			json_map.put("message","Use Get request to use this service");
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
		System.out.println("Inside POST call of GetMapData!!");
		Map<String, String> json_map = new HashMap<String, String>();
		
			json_map.put("status","Error");
			json_map.put("message","Use GET request to use this service");
		
		String json_response = new Gson().toJson(json_map);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json_response);
	}

}

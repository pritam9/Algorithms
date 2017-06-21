package app.one.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.one.dao.DaoActions;
import app.one.model.MapData;
import app.one.model.Response;

public class UpdateMapData extends HttpServlet{
	public UpdateMapData() {
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
		
		
			Map<String, String> json_map = new HashMap<String, String>();
			json_map.put("status","Error");
			json_map.put("message","Use POST request to use this service");
			String json_response = new Gson().toJson(json_map);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside POST call of GetMapData!!");
		Map<String, String> json_map = new HashMap<String, String>();
		
		int id = Integer.parseInt(request.getParameter("id"));
		double cost = Double.parseDouble(request.getParameter("cost"));
		double sales = Double.parseDouble(request.getParameter("sales"));
		
		Response my_response = new Response();
		RequestDispatcher rd = null;
		
		if(DaoActions.updateMapData(id,cost,sales)){
			
			json_map.put("status","Success");
			
		}
		else{
			json_map.put("status","Error");
			json_map.put("message","Unable to store new value");
		}
		
		String json_response = new Gson().toJson(json_map);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json_response);
	}

}

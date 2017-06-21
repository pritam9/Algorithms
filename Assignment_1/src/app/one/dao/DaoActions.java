package app.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.one.model.User;



public class DaoActions {

	public static boolean isValidUser(User userBean) {
		// TODO Auto-generated method stub
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  System.out.println("User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = null;
	          query = "select * from login where username=? and password=?";
	           //System.out.println("123");   
	        stmt = connection.prepareStatement(query);
	        //System.out.println("456");   
	        stmt.setString(1, userBean.getUsername());
	        stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        ResultSet rs=stmt.executeQuery();
	        //System.out.println("789");
	        if(rs.next())
	        	return true;
	      
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return false;
	}
	
	public static boolean insertValid(){
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = null;
	          query = "insert into testDate values(NOW(),?)";
	           //System.out.println("123");   
	        stmt = connection.prepareStatement(query);
	        stmt.setString(1, "Test1");
	        //System.out.println("456");   
	        //stmt.setString(1, userBean.getUsername());
	        //stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        stmt.executeUpdate();
	        //System.out.println("789");
	        //if(rs.next())
	        	return true;
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return false;
	}
	
}

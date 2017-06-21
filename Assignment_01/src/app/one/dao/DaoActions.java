package app.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.soap.SOAPBinding.Use;

import app.one.model.MapData;
import app.one.model.MockUsers;
import app.one.model.Products;
import app.one.model.User;
import app.one.util.AESencrp;



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
	        {
	        	String passwordEnc;
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					System.out.println(df.format(dateobj));
					passwordEnc = AESencrp.encrypt(df.format(dateobj));
					System.out.println("Token is  : " + passwordEnc);
					query = "UPDATE auth_tokens"
							+ " SET token = ? "
							+ " WHERE username = ?";
		        	stmt = connection.prepareStatement(query);
		        	stmt.setString(2, userBean.getUsername());
			        stmt.setString(1, passwordEnc);
			        //stmt.setString(3, userBean.getName());
			        stmt.executeUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //String passwordDec = AESencrp.decrypt(passwordEnc);

	            //System.out.println("Plain Text : " + password);
	            
	            //System.out.println("Decrypted Text : " + passwordDec);
	        	return true;
	        }
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return false;
	}
	
	public static boolean isNewUser(User userBean) {
		// TODO Auto-generated method stub
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  System.out.println("New User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = null;
	          query = "select * from login where username=?";
	           //System.out.println("123");   
	        stmt = connection.prepareStatement(query);
	        //System.out.println("456");   
	        stmt.setString(1, userBean.getUsername());
	        //stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        
	        ResultSet rs=stmt.executeQuery();
	        //System.out.println("789");
	        if(rs.next())
	        	return false;
	        else
	        {
	        	query = "insert into login (username, password, full_name) values (?,?,?)";
	        	stmt = connection.prepareStatement(query);
	        	stmt.setString(1, userBean.getUsername());
		        stmt.setString(2, userBean.getPassword());
		        System.out.println("New User is - "+userBean.getName());
		        stmt.setString(3, userBean.getName());
		        stmt.executeUpdate();
		        query = "insert into auth_tokens (username) values (?)";
	        	stmt = connection.prepareStatement(query);
	        	stmt.setString(1, userBean.getUsername());
		        //stmt.setString(2, userBean.getPassword());
		        //stmt.setString(3, userBean.getName());
		        stmt.executeUpdate();
		        return true;
		        
	        }
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return false;
	}

	
	// get Product Details
	
	public static List<Products> getProductList(String region) {
		// TODO Auto-generated method stub
		List<Products> productList = new ArrayList<Products>();
		try { 
			System.out.println("inside get Product function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = null;
	          query = "select * from Products where region=?";
	           //System.out.println("123");   
	        if (region.equals("all"))
	        {
	        	query = "select * from Products ORDER BY region";
	        	stmt = connection.prepareStatement(query);
	        }
	        else{
	        	query = "select * from Products where region=?";
	        	stmt = connection.prepareStatement(query);
		        //System.out.println("456");   
		        stmt.setString(1,region);
	        }
	          
	        //stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        
	        ResultSet rs=stmt.executeQuery();
	        //System.out.println("789");
	        while(rs.next())
	        {
	        	Products product = new Products();
	        	product.setName(rs.getString(1));
	        	product.setDiscount(rs.getString(2));
	        	product.setUrl(rs.getString(3));
	        	product.setPrice(rs.getString(4));
	        	product.setRegion(rs.getString(5));
	        	productList.add(product);
	        }
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return productList;
	}
	
	
	
	public static String getToken(User user) {
		// TODO Auto-generated method stub
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  System.out.println("New User details are as follows : username -"+user.getUsername()+ " and Password is - "+user.getPassword());
	    	 String query = null;
	          query = "select token from auth_tokens where username=?";
	           //System.out.println("123");   
	        stmt = connection.prepareStatement(query);
	        //System.out.println("456");   
	        stmt.setString(1, user.getUsername());
	        //stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        
	        ResultSet rs=stmt.executeQuery();
	        //System.out.println("789");
	        while(rs.next())
	        {
	        	return rs.getString(1);
	        }
	        
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		
		return "Token Expired/Invalid";
	}
	
	public static User getProfileDetails(String token){
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+user.getUsername()+ " and Password is - "+user.getPassword());
	    	 String query = null;
	          query = "select username from auth_tokens where token=?";
	           //System.out.println("123");   
	        stmt = connection.prepareStatement(query);
	        //System.out.println("456");   
	        stmt.setString(1, token);
	        //stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        
	        ResultSet rs=stmt.executeQuery();
	        //System.out.println("789");
	        while(rs.next())
	        {
	        	String username = rs.getString(1);
	        	query = "select full_name from login where username=?";
		           //System.out.println("123");   
		        stmt = connection.prepareStatement(query);
		        //System.out.println("456");   
		        stmt.setString(1, username);
		        //stmt.setString(2, userBean.getPassword());
		       
		        //System.out.println("abc");   
		        
		        ResultSet rs1=stmt.executeQuery();
		        while(rs1.next())
		        {
		        	User user = new User();
		        	user.setUsername(username);
		        	user.setName(rs1.getString(1));
		        	
		        	//add new token information after use;
		        	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					System.out.println(df.format(dateobj));
					String passwordEnc = AESencrp.encrypt(df.format(dateobj));
		        	query = "UPDATE auth_tokens"
							+ " SET token = ? "
							+ " WHERE username = ?";
		        	stmt = connection.prepareStatement(query);
		        	stmt.setString(2, username);
		        	stmt.setString(1, passwordEnc);
			        //stmt.setString(1, passwordEnc);
			        //stmt.setString(3, userBean.getName());
			        stmt.executeUpdate();
			        user.setToken(passwordEnc);
		        	return user;
		        }
	        }
	        
	        
	              
	    } catch (Exception ex) {
	        
	        System.out.println("Exception in getProfile Data customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return null;
	}
	
	
	
	//Get Profile details based on username:
	
	public static User getMyDetails(String username){
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+user.getUsername()+ " and Password is - "+user.getPassword());
	    	 String query = null;
	         
	        	query = "select full_name from login where username=?";
		           //System.out.println("123");   
		        stmt = connection.prepareStatement(query);
		        //System.out.println("456");   
		        stmt.setString(1, username);
		        //stmt.setString(2, userBean.getPassword());
		       
		        //System.out.println("abc");   
		        
		        ResultSet rs1=stmt.executeQuery();
		        while(rs1.next())
		        {
		        	User user = new User();
		        	user.setUsername(username);
		        	user.setName(rs1.getString(1));
		        	return user;
		        }
	        
	        
	        
	              
	    } catch (Exception ex) {
	        
	        System.out.println("Exception in getProfile Data customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return null;
	}

	public static void clearToken(String username) {
		// TODO Auto-generated method stub
		try { 
			System.out.println("inside isValidUser function");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+user.getUsername()+ " and Password is - "+user.getPassword());
	    	 String query = null;
	    	 
				query = "UPDATE auth_tokens"
						+ " SET token = 'INVALID' "
						+ " WHERE username = ?";
	        	stmt = connection.prepareStatement(query);
	        	stmt.setString(1, username);
		        //stmt.setString(1, passwordEnc);
		        //stmt.setString(3, userBean.getName());
		        stmt.executeUpdate();
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		
	}

	public static List<MockUsers> getUserList(int page, String asc_order, String order_by) {
		// TODO Auto-generated method stub
		List<MockUsers> userList = new ArrayList<MockUsers>();
		try { 
			System.out.println("inside get users function - "+order_by);
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = "SELECT * FROM USER_MOCK_DATA ORDER BY "+order_by+" desc LIMIT "+page+",50";
	    	 if(asc_order.equals("asc"))
	    		 query = "SELECT * FROM USER_MOCK_DATA ORDER BY "+order_by+" asc LIMIT "+page+",50";
	           //System.out.println("123");   
	        
	        	//query = "select * from Products where region=?";
	        	stmt = connection.prepareStatement(query);
		        //System.out.println("456");   
		        //stmt.setString(1,order_by);
		        //stmt.setString(2, asc_order);
		        //stmt.setInt(2, page);
	          
	        //stmt.setString(2, userBean.getPassword());
	       
	        //System.out.println("abc");   
	        
	        ResultSet rs=stmt.executeQuery();
	        //System.out.println("789");
	        while(rs.next())
	        {
	        	MockUsers user = new MockUsers(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
	        	
	        	userList.add(user);
	        }
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return userList;
	}

	public static List<MapData> getMapDataList() {
		// TODO Auto-generated method stub
		List<MapData> mapDataList = new ArrayList<MapData>();
		try { 
			System.out.println("inside get users function - getMapDataList()");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = "SELECT * FROM data";
	    	 stmt = connection.prepareStatement(query);
		     ResultSet rs=stmt.executeQuery();
	        while(rs.next())
	        {
	        	MapData mapData = new MapData();
	        	mapData.setId(rs.getInt(1));
	        	mapData.setCost(rs.getDouble(2));
	        	mapData.setSales(rs.getDouble(3));
	        	mapData.setItem(rs.getString(4).replaceAll("(\\r|\\n)", ""));
	        	mapDataList.add(mapData);
	        }
	        
	              
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return mapDataList;
	}

	public static boolean updateMapData(int id, double cost, double sales) {
		// TODO Auto-generated method stub
		try { 
			System.out.println("inside get users function - updateMapData()");
	    	Connection connection = null;
	    	 PreparedStatement stmt = null;
	    	 Dao connectionData=new Dao();
	    	  connection = connectionData.getConnection();
	    	  //System.out.println("New User details are as follows : username -"+userBean.getUsername()+ " and Password is - "+userBean.getPassword());
	    	 String query = "UPDATE data SET cost = ?, sales = ? where id = ?";
	    	 stmt = connection.prepareStatement(query);
	    	 stmt.setInt(3, id);
	    	 stmt.setDouble(1, cost);
	    	 stmt.setDouble(2, sales);
	    	 return stmt.executeUpdate() > 0; 
		        
	    } catch (SQLException ex) {
	        
	        System.out.println("Exception in add customer method");
	             
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return false;
	}
	
}

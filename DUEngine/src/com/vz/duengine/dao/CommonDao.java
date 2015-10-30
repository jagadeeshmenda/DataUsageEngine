package com.vz.duengine.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommonDao {
	
	
	
	public Connection getDbConnction () {
		
		try {
	        System.out.println("Loading driver...");
	        
	        Class.forName("org.postgresql.Driver");
	        Class.forName("oracle.jdbc.OracleDriver");
	        System.out.println("Driver loaded!");
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Cannot find the driver in the classpath!", e);
	    }
		
	    String url = "jdbc:postgresql:DataUsageEngine"; // Important
	    String username = "postgres";
	    String password = "root";
	    Connection connection = null;
	    try {
	        System.out.println("Connecting database...");
	        //con = DriverManager.getConnection("jdbc:postgresql:DataUsageEngine","postgres","root");
	        connection = DriverManager.getConnection(url, username, password);
	        
	        System.out.println("Got the connection " + connection);
	        System.out.println("Database connected!");                      
	    } catch (Exception e) {
	        e.printStackTrace();
	       // throw new RuntimeException("Cannot connect the database!", e);
	    } 
	    return connection;
	}
	
	public static void main (String args[]){
		CommonDao common  = new CommonDao () ;
		common.getDbConnction();
	}
	
	public void closeConnection (Connection con ) {
		try {
			if (con  != null ) {
				con.close();
			}
		}catch (Exception ex) {
			
		}
		
	}

}

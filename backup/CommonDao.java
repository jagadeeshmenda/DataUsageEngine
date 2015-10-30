
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonDao {
	
	
	
	public Connection getDbConnction () {
		
		try {
	        System.out.println("Loading driver...");
	        Class.forName("oracle.jdbc.OracleDriver");
	        System.out.println("Driver loaded!");
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Cannot find the driver in the classpath!", e);
	    }
		
	    String url = "jdbc:oracle:thin:@113.128.163.241:1521:xe"; // Important
	    String username = "SYSTEM";
	    String password = "password";
	    Connection connection = null;
	    try {
	        System.out.println("Connecting database...");
	        connection = DriverManager.getConnection(url, username, password);
	        
	        System.out.println("Got the connection " + connection);
	        System.out.println("Database connected!");                      
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Cannot connect the database!", e);
	    } finally {
	        System.out.println("Closing the connection.");
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	    return connection;
	}
	
	public static void main (String args[]){
		CommonDao common  = new CommonDao () ;
		common.getDbConnction();
	}

}

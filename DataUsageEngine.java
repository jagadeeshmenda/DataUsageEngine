import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DataUsageEngine {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			con = DriverManager.getConnection("jdbc:oracle:thin:@113.128.163.241:1521:xe", "SYSTEM","password");
			pstmt = con.prepareStatement("SELECT DISTINCT(STATE) FROM VZ_CUSTOMER_INFO");
			System.out.println("before executing query");
			rs = pstmt.executeQuery();
			while(rs.next()){
				String state = rs.getString(1);
				new DataUsageThread(state).start();
			}		
			/*new DataUsageThread("eBay").start();
			new DataUsageThread("Paypal").start();
			new DataUsageThread("Google").start();*/

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}if(pstmt != null){
					pstmt.close();
				}if(con != null){
					con.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}

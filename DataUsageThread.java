import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DataUsageThread extends Thread {
	public DataUsageThread(String str) {
		super(str);
	}
 
	public void run() {
		//for (int i = 0; i < 5; i++) {
			//System.out.println("Loop " + i + ": " + getName());
			try {
				usageNotify();
				sleep((int) (Math.random() * 2000));
			} catch (InterruptedException e) {
			}
		//}
		System.out.println("Test Finished for: " + getName());
	}
	public void usageNotify(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			con = DriverManager.getConnection("jdbc:oracle:thin:@113.128.163.241:1521:xe", "SYSTEM","password");
			pstmt = con.prepareStatement("SELECT CUST_ID FROM VZ_USAGE_NOTIFICATION WHERE MONTHLY_AGG >=105 ");
			System.out.println("before executing query");
			rs = pstmt.executeQuery();
			while(rs.next()){
				String custId = rs.getString(1);
				sendNotification(custId);
			}	
			
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
	public void sendNotification(String custId){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			con = DriverManager.getConnection("jdbc:oracle:thin:@113.128.163.241:1521:xe", "SYSTEM","password");
			pstmt = con.prepareStatement("UPDATE VZ_USAGE_NOTIFICATION SET NOTIFICATION_SENT='Y'WHERE CUST_ID = ? ");
			System.out.println("before executing query");
			pstmt.setString(1, custId);
			int count = pstmt.executeUpdate();
			if(count == 0){
				System.out.println("Notification sent to Customer");
			}
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PostGreApp {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql:DataUsageEngine","postgres","root");
			pstmt = con.prepareStatement("SELECT web_id, website FROM vz_free_website");
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}

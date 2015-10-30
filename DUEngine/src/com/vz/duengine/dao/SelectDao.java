package com.vz.duengine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vz.duengine.model.NotifyModel;
import com.vz.duengine.threads.DataUsageThread;

public class SelectDao extends CommonDao {
	
	public ArrayList getStateList () {
		ArrayList arrayList  =  new ArrayList () ;
		
		Connection con  = getDbConnction();
		try {
			
			PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT(STATE) FROM VZ_CUSTOMER_INFO");
			System.out.println("before executing query");
			ResultSet rs = pstmt.executeQuery();
			while(rs .next()){
				String state = rs .getString(1);
				arrayList.add(state);
			}		
			
		}catch (Exception ex ){
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			}catch(Exception ex) {
		}
		}
		return arrayList ;
	}
	
	
	public ArrayList getNotifyDetils () {
		Connection con  = null ;
		ArrayList <NotifyModel> nm  =  new ArrayList () ;
		try  {
			con  = getDbConnction() ;
			PreparedStatement pstmt = con.prepareStatement("SELECT CUST.EMAIL, CUST_EMAIL, CUST_ID FROM VZ_USAGE_NOTIFICATION WHERE MONTHLY_AGG >=105 ");
			ResultSet result  = pstmt.executeQuery() ;
			
			while (result.next()){
				NotifyModel notifyModel  =  new NotifyModel ();
				notifyModel.setState(result.getString("STATE"));
				notifyModel.setUsage(result.getLong("Usage"));
				notifyModel.setEmail(result.getString("Usage"));
			nm.add(notifyModel);
			}
		}catch (Exception ex) {
			
		}
		
		return nm ;
	}
	
	
	public void updateRecord  (NotifyModel notify) {
		
		

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection con =  null; 
			try{
				con  = getDbConnction() ;
				pstmt = con.prepareStatement("UPDATE VZ_USAGE_NOTIFICATION SET NOTIFICATION_SENT='Y'WHERE CUST_ID = ? ");
				System.out.println("before executing query");
				pstmt.setString(1, notify.getEmail());
				int count = pstmt.executeUpdate();
				if(count == 0){
					System.out.println("Notification sent to Customer");
				}
		}catch (Exception ex ) {
			
		}
	}

}

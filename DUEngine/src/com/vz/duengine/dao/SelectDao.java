package com.vz.duengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vz.duengine.model.NotifyModel;

public class SelectDao extends CommonDao {
	
	public ArrayList getStateList () {
		ArrayList arrayList  =  new ArrayList () ;
		
		
		try {
			
			
		}catch (Exception ex ){
			ex.printStackTrace();
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
				notifyModel.setEmail(result.getString("STATE"));
			nm.add(notifyModel);
			}
		}catch (Exception ex) {
			
		}
		
		return nm ;
	}

}

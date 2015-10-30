package com.vz.duengine.threads;

import java.util.List;

import com.vz.duengine.dao.SelectDao;
import com.vz.duengine.email.SendMail;
import com.vz.duengine.model.NotifyModel;

public class NotifyProcessor extends Thread {
	
	 NotifyModel notifymodel= null; 
	 String processorName ;
	
	
	public NotifyProcessor (String serviceState ) {
		processorName = "Deamon_processor- "+ serviceState; 
	}
	
	
	public void run () { 
		
		try { 
			SelectDao select  =  new SelectDao() ;
			
			List <NotifyModel> lst  = select.getNotifyDetils() ;
			
			for (NotifyModel notify : lst) {
				SendMail sendmail  =  new SendMail ();
				sendmail.sendEmail(notify);
				select.updateRecord (notify);
			}
			
		}catch(Exception ex) {
			 ex.printStackTrace();
		}
	}


	

}

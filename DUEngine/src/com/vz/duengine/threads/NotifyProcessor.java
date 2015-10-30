package com.vz.duengine.threads;

import com.vz.duengine.email.SendMail;
import com.vz.duengine.model.NotifyModel;

public class NotifyProcessor {
	
	 NotifyModel notifymodel= null; 
	 String processorName ;
	
	
	public NotifyProcessor (String serviceState ) {
		processorName = "Deamon_processor- "+ notifymodel.getState(); 
	}
	
	
	public void run () { 
		
		SendMail sendmail  =  new SendMail ();
		sendmail.sendEmail(notifymodel);
	}

}

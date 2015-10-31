package com.vz.duengine.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.vz.duengine.model.NotifyModel;

public class SendMail {

	public void sendEmail(NotifyModel notifyModel) {

		final String username = "noreply.vzdatausage@gmail.com";
		final String password = "Gladitors123";

		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		
		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));
		
		// set the less security level at  
		//https://www.google.com/settings/security/lesssecureapps
		// source - Google Docs.
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.vzdatausage@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(notifyModel.getEmail()));
			String subject  = " Data Usage alert..!" ;
			String body = " Dear Customer,  \n\nYout total Data Usage :" + notifyModel.getUsage() /1024 /1024 + "MB\n\n\n Regards, \nGladitors";
			
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			System.out.println();
			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("sent email..");
		}
	}
	
	
	public static void main(String[] args){
		NotifyModel nfm  =  new NotifyModel () ;
		
		nfm.setEmail("jagadee.me@gmail.com,jasmeet.iimc@gmail.com");
		nfm.setState("AP");
		nfm.setUsage(2000000000);
		SendMail sm  = new SendMail (); 
		sm.sendEmail(nfm);
		
		
	}
	
	
}

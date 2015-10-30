package com.vz.duengine.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.vz.duengine.model.NotifyModel;

public class SendMail {

	public static void sendEmail(NotifyModel notifyModel) {

		final String username = "chiddu.m@gmail.com";
		final String password = "1234567";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("chiddu.m@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(notifyModel.getEmail()));
			String subject  = " Data Usage alert.." ;
			String body = "Yout total Data Usage :" + notifyModel.getUsage();
			
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
}

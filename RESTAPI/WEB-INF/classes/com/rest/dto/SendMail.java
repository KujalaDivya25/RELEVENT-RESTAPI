package com.rest.dto;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SendMail {

	public static void sendMail(String to, String subject, String text) {

		String from = "releventmyproject@gmail.com";
		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");

		Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("releventmyproject@gmail.com","relevent2020");
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			System.out.println("sentmessage successfully");

		}catch (MessagingException mex) {
			mex.printStackTrace();

		}

	}

}
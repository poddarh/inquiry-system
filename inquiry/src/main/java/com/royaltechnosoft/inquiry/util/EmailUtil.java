package com.royaltechnosoft.inquiry.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public static boolean sendEmail(String to, String subject, String body) {

		final String fromEmail = "technosoftroyal@gmail.com"; // requires valid gmail id
		final String password = "inquirysystem"; // correct password for gmail id

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			// msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// msg.addHeader("format", "flowed");
			// msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

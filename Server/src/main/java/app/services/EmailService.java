package app.services;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailService {
    public void send(String text, List<String> mails) {
        // Sender's email ID needs to be mentioned
        String from = "slkhvaRamina@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        };
        Session session = Session.getInstance(props, auth);
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.user", "ramina270102@gmail.com");
//        properties.setProperty("mail.password", "Mishka27012002");
////        properties.setProperty("port", String.valueOf(587));
//        // Setup mail server
//        properties.setProperty("smtp.gmail.com", host);
//
//        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties);
        try {
            // Create a default MimeMessage object.
            for (String mail : mails) {
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));

                // Set Subject: header field
                message.setSubject("Поликлиника.Оповещение");

                // Now set the actual message
                message.setText(text);

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

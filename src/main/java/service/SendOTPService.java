package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class SendOTPService {
    public static void sendOTP(String email, String genOTP){
        // Recipient's email ID needs to be mentioned .
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "syedulazam6000@gmail.com";

        // Assuming we are sending email from gmail's smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        // Get the Session object.// and pass the username and password
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "hhvylcpdqncgntvk");
            }
        };

        // Get the Session object and pass the authenticator
        Session session = Session.getInstance(properties, authenticator);
        // Used to debug SMTP issues
        session.setDebug(true);

        try{
            // Creating a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("File Enc's OTP");

            // Now set the actual message
            message.setText("Your one time password for file ENC app is" + genOTP);

            System.out.println("Sending.....");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully");


        }catch (MessagingException e){
            e.printStackTrace();

        }
    }
}

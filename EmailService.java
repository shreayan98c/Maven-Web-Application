package in.vakrangee.vkms.utilities;

// Author: Shreayan Chaudhary
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.InetAddress;
import javax.activation.*;

public class EmailService {

    public static void send_email(String sender_id, String recipient_id, String subject, String body) {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.host","localhost");
//        props.put("mail.store.protocol", "pop3");
        props.put("mail.debug", "true");
        props.put("mail.transport.protocol", "smtp");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("shreayan.chaudhary.project@gmail.com", "P@ssword1234");
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender_id));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient_id));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            System.out.println("Message sent successfully!!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
//    public static void main(String [] args) {
//        send_email("shreayan.chaudhary.project@gmail.com", "shreayan_sameer@srmuniv.edu.in", "Hello World!", "You're Awesome!");
//    }
}
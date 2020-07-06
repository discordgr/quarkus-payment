package org.acme.service;


import com.sun.mail.smtp.SMTPMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import javax.inject.Singleton;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Singleton
public class EmailService {

    private JavaMailSenderImpl javaMailSender;
    private SMTPMessage smtpMessage;
    private Session session;

    public EmailService(){

        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(25);

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.from", "panayiotismilios@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        final PasswordAuthentication usernamePassword = new PasswordAuthentication("panayiotismilios@gmail.com", "panayiotis231993");
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return usernamePassword;
            }
        };

        session = Session.getInstance(props, auth);
        session.setDebug(true);
        smtpMessage = new SMTPMessage(session);
    }

    public void sendEmail(String emailTo, String content) {
        try {
            smtpMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailTo));
            smtpMessage.setSubject("CardLink Receipt");
            smtpMessage.setText(content);
            Transport.send(smtpMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

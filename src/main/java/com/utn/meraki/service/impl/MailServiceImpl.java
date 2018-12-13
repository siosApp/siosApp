package com.utn.meraki.service.impl;

import com.utn.meraki.service.MailService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private void sendMail(String destinatario,String asunto,String cuerpo){
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "nico22utn@gmail.com");
        props.put("mail.smtp.password", "Didierdrogba38911895,");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);



        Session session = Session.getInstance(props,null);
        MimeMessage message = new MimeMessage(session);

        System.out.println("Port: "+session.getProperty("mail.smtp.port"));

        // Create the email addresses involved
        try {
            InternetAddress from = new InternetAddress("nico22utn");
            message.setSubject(asunto);
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");

            // Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(cuerpo);

            // Add the text part to the multipart
            multipart.addBodyPart(messageBodyPart);

            // Create the html part
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = cuerpo;
            messageBodyPart.setContent(htmlMessage, "text/html");


            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);

            // Associate multi-part with message
            message.setContent(multipart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "nico22utn", "Didierdrogba38911895,");
            System.out.println("Transport: "+transport.toString());
            transport.sendMessage(message, message.getAllRecipients());


        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    @Async
    public void enviarMail(String destinatario, String asunto, String cuerpo) {
        sendMail(destinatario,asunto,cuerpo);
    }
}

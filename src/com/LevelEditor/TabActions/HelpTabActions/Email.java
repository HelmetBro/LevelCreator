package com.LevelEditor.TabActions.HelpTabActions;

import com.LevelEditor.ScreenComponents.InfoLabelButton;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class Email {

    private static final String FROM = "eplevelcreator@gmail.com";
    private static final String TO = "eplevelcreator@gmail.com";

    private String subject;
    private String body;

    private Session session;

    public Email(String subject, String body) {

        this.subject = subject;
        this.body = body;

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        props.put("mail.smtps.quitwait", "false");

        session = Session.getInstance(props, null);

    }

    public void send() {

        if (body == null) {
            InfoLabelButton.updateLabelText("Canceled.");
            return;
        }

        InfoLabelButton.updateLabelText("Sending...");

        new Thread(() -> {

            try {

                final MimeMessage msg = new MimeMessage(session);

                msg.setFrom(new InternetAddress(FROM));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO, false));

                msg.setSubject(subject);
                msg.setText(body, "utf-8");
                msg.setSentDate(new Date());

                SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

                t.connect("smtp.gmail.com", FROM, "MDcjanf#^#$(VasFMF(@)$m2");
                t.sendMessage(msg, msg.getAllRecipients());
                t.close();

                InfoLabelButton.updateLabelText("Sent - Thanks!");

            } catch (MessagingException e) {
                InfoLabelButton.updateLabelText("Can't Send!");
            }

        }).start();

    }//send

}//Email class

package org.example

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.Email
import org.apache.commons.mail.SimpleEmail

class EmailService {

    public static void send(String message, String receiverMail){

        Properties properties = new Properties();
        InputStream mailSend = new FileInputStream("./griffon-app/resources/config.properties")
        properties.load(mailSend)

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(properties.getProperty("senderMail"), properties.getProperty("password")));
        email.setSSLOnConnect(true);
        email.setFrom("user@gmail.com");
        email.setSubject("TestMail");
        email.setMsg(message);
        email.addTo(receiverMail);
        email.send();
        println("the letter is sent")
    }
}

package org.example

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.MultiPartEmail

class EmailService {

    static void send(String message, String receiverMail, String fileName, Properties properties){

        EmailAttachment attachment = new EmailAttachment()
        attachment.setPath(fileName)
        MultiPartEmail email = new MultiPartEmail()
        email.setHostName(properties.getProperty("hostName"))
        email.setSmtpPort(properties.getProperty("smtpPort") as int)
        email.setAuthenticator(new DefaultAuthenticator(properties.getProperty("senderMail"), properties.getProperty("password")))
        email.setSSLOnConnect(true)
        email.setFrom(properties.getProperty("user"))
        email.setSubject(properties.getProperty("subject"))
        email.setMsg(message)
        email.addTo(receiverMail)
        email.attach(attachment)
        email.send()
    }
}

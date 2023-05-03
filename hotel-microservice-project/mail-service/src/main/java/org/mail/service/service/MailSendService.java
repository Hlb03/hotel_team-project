package org.mail.service.service;

import org.mail.service.mail_types.GeneralMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public MailSendService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(String to, GeneralMail mail, String url) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailFrom);
        message.setTo(to);
        message.setSubject(mail.getMailHeader());
        message.setText(mail.getMailText() + "\n\n" + url);

        mailSender.send(message);
    }
}

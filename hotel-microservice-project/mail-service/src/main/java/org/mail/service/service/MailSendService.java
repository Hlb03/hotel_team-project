package org.mail.service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.mail.service.mail_types.GeneralMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public MailSendService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(String to, String userLastName, GeneralMail mail, String activationCode) {
        String tempReceiver = "gfietisov@gmail.com";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String code = "<h3 style='font-weight: bold; color: blue'>" + activationCode + "</h3>";

        try {
            helper.setTo(tempReceiver);
            helper.setFrom(mailFrom);
            helper.setSubject(mail.getMailHeader() + userLastName);
            helper.setText(mail.getMailText() + "<br><br><br>" + code, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(mimeMessage);
    }
}

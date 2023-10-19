package org.mail.service.service.implementations;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.mail_types.GeneralMail;
import org.mail.service.service.MailSendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendMessage(MailRequestDTO requestDTO, String activationCode, GeneralMail mail) throws MessagingException {
        String tempReceiver = "gfietisov@gmail.com";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String code = "<h3 style='font-weight: bold; color: blue'>" + activationCode + "</h3>";

        helper.setTo(tempReceiver);
        helper.setFrom(mailFrom);
        helper.setSubject(mail.getMailHeader() + requestDTO.getLastName());
        helper.setText(mail.getMailText() + "<br><br><br>" + code, true);

        mailSender.send(mimeMessage);
    }
}

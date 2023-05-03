package org.mail.service.mail_types;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mail.properties")
public class ActivationMail extends GeneralMail {

    @Value("${activation.mail.header}")
    private String mailHeading;

    @Value("${activation.mail.body}")
    private String mainPart;

    @Override
    public String getMailHeader() {
        return mailHeading;
    }

    @Override
    public String getMailText() {
        return mainPart;
    }

}

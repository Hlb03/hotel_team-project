package org.mail.service.controller;

import lombok.AllArgsConstructor;
import org.mail.service.mail_types.ActivationMail;
import org.mail.service.service.MailSendService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {

    private final MailSendService mailService;
    private final ActivationMail activationMail;


    // Params: activation code, user To receive message, name of receiver
    @PostMapping("/activate/{activationCode}")
    public String activateAccount(@PathVariable String activationCode, @RequestParam String receiver) {
        mailService.sendMessage(receiver, activationMail, "http://MAIN-SERVICE/hotel-rent/users/1");

        return "Message was sent";
    }
}

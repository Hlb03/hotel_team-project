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

    @PostMapping("/activate")
    public String activateAccount(@RequestParam String receiver,
                                  @RequestParam String username,
                                  @RequestParam String activationCode) {
        System.out.println("INPUT PARAMS: " + activationCode + " " + receiver + " " + username);
        mailService.sendMessage(receiver, username, activationMail, activationCode);

        return "Message was sent";
    }
}

package org.mail.service.controller;

import lombok.AllArgsConstructor;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.mail_types.ActivationMail;
import org.mail.service.service.MailSendService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {

    private final MailSendService mailService;
    private final ActivationMail activationMail;

    @PostMapping("/activate/{activationCode}")
    @ResponseStatus(HttpStatus.OK)
    public String activateAccount(@PathVariable String activationCode,
                                  @RequestBody MailRequestDTO requestDTO
    ) {
        System.out.println("INPUT PARAMS: " + activationCode + " " + requestDTO);
        mailService.sendMessage(requestDTO.getReceiver(), requestDTO.getUsername(), activationMail, activationCode);

        return "Message was sent";
    }
}

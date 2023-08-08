package org.main.service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.exceptions.InvalidActivationCodeException;
import org.main.service.service.AccountStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@AllArgsConstructor
public class UserAccountStatusController {

    private final AccountStatusService accountStatusService;

    @PostMapping("activate-account/{activationCode}")
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@PathVariable String activationCode) {
        try {
            accountStatusService.activateUserAccount(activationCode);
        } catch (InvalidActivationCodeException e) {
            log.info("FAILED TO ACTIVATE ACCOUNT. WRONG ACTIVATION KEY");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("block-account/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void blockAccount(@PathVariable Integer userId) {
        accountStatusService.blockUserAccountById(userId);
    }

    @PostMapping("unblock-account/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void unblockAccount(@PathVariable Integer userId) {
        accountStatusService.unblockAccountById(userId);
    }
}

package com.sg.kata.api;

import com.sg.kata.model.Account;
import com.sg.kata.request.AccountRequest;
import com.sg.kata.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private final AccountService accountService;

    AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }
}

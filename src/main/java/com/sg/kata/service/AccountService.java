package com.sg.kata.service;

import com.sg.kata.exception.ValidationException;
import com.sg.kata.model.Account;
import com.sg.kata.repository.AccountRepository;
import com.sg.kata.request.AccountRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountRequest accountRequest) {
        validateAccountToSave(accountRequest);
        var accountToSave = Account.builder()
                .balance(accountRequest.getBalance())
                .owner(accountRequest.getOwner())
                .creationDate(LocalDateTime.now())
                .build();
        return accountRepository.save(accountToSave);
    }

    private void validateAccountToSave(AccountRequest accountRequest) {
        if (Objects.isNull(accountRequest)) {
            throw new ValidationException("Cannot add a null account");
        }
        if (Objects.isNull(accountRequest.getOwner())) {
            throw new ValidationException("Account owner is mandatory");
        }
        if (Objects.isNull(accountRequest.getBalance())) {
            throw new ValidationException("Account balance is mandatory");
        }
    }
}

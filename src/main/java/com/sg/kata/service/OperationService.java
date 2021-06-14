package com.sg.kata.service;

import com.sg.kata.exception.ValidationException;
import com.sg.kata.model.Account;
import com.sg.kata.model.Operation;
import com.sg.kata.model.OperationType;
import com.sg.kata.repository.AccountRepository;
import com.sg.kata.repository.OperationRepository;
import com.sg.kata.request.OperationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OperationService {

    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;

    OperationService(OperationRepository operationRepository,
                     AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public List<Operation> getOperations(Integer accountId) {
        return operationRepository.findOperationsByAccountId(accountId);
    }

    public Operation deposit(OperationRequest operationRequest) {
        var account = getAccountById(operationRequest.getAccountId());
        var amount = operationRequest.getAmount();
        if (amount < 0) {
            throw new ValidationException("Cannot deposit a negative amount");
        }
        var newBalance = account.getBalance() + amount;
        return newOperation(account.getId(), OperationType.DEPOSIT, amount, account.getBalance(), newBalance);
    }

    public Operation retrieve(OperationRequest operationRequest) {
        var account = getAccountById(operationRequest.getAccountId());
        var amount = operationRequest.getAmount();
        if (amount > account.getBalance()) {
            throw new ValidationException("Cannot retrieve an amount grether than your balance");
        }
        var newBalance = account.getBalance() - amount;
        return newOperation(account.getId(), OperationType.RETRIEVE, amount, account.getBalance(), newBalance);
    }

    private Operation newOperation(Integer accountId, OperationType operationType, Double amount,
                                   Double oldBalance, Double newBalance) {
        accountRepository.updateBalance(newBalance, accountId);
        var operation = buildOperation(accountId, operationType, amount, oldBalance, newBalance);
        return operationRepository.save(operation);
    }

    private Account getAccountById(Integer accountId) {
        return accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account with ID " + accountId + " is not found"));
    }

    private Operation buildOperation(Integer accountId, OperationType operationType, Double amount,
                                     Double oldBalance, Double newBalance) {
        return Operation.builder()
                .type(operationType)
                .amount(amount)
                .oldBalance(oldBalance)
                .newBalance(newBalance)
                .date(LocalDateTime.now())
                .account(Account.builder()
                        .id(accountId)
                        .build())
                .build();
    }
}

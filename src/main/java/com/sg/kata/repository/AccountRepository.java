package com.sg.kata.repository;

import com.sg.kata.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Query("UPDATE Account a SET a.balance =:balance WHERE a.id =:accountId ")
    void updateBalance(Double balance, Integer accountId);
}

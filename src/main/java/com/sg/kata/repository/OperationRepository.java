package com.sg.kata.repository;

import com.sg.kata.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

    List<Operation> findOperationsByAccountId(Integer accountId);
}

package com.sg.kata.api;

import com.sg.kata.model.Operation;
import com.sg.kata.request.OperationRequest;
import com.sg.kata.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationRestController {

    private final OperationService operationService;

    OperationRestController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Operation>> getOperations(@PathVariable Integer accountId) {
        return new ResponseEntity<>(operationService.getOperations(accountId), HttpStatus.OK);
    }

    @PostMapping("/retrieve")
    public ResponseEntity<Operation> retrieve(@RequestBody OperationRequest operationRequest) {
        return new ResponseEntity<>(operationService.retrieve(operationRequest), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Operation> deposit(@RequestBody OperationRequest operationRequest) {
        return new ResponseEntity<>(operationService.deposit(operationRequest), HttpStatus.OK);
    }
}

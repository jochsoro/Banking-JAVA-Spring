package com.chonfoungo.banking.controllers;

import com.chonfoungo.banking.dto.ContactDto;
import com.chonfoungo.banking.dto.TransactionDto;
import com.chonfoungo.banking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }

    @GetMapping("/")
    public  ResponseEntity<List<TransactionDto>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{transaction-idd}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id") Integer transactionId) {
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(transactionService.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("transaction-id") Integer transactionId
    ){
        transactionService.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}

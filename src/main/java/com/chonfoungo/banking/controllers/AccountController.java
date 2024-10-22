package com.chonfoungo.banking.controllers;

import com.chonfoungo.banking.dto.AccountDto;
import com.chonfoungo.banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @GetMapping("/")
    public  ResponseEntity<List<AccountDto>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{account-idd}")
    public ResponseEntity<AccountDto> findById(@PathVariable("account-id") Integer accountId) {
        return ResponseEntity.ok(accountService.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("account-id") Integer accountId
    ){
        accountService.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}

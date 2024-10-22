package com.chonfoungo.banking.controllers;

import com.chonfoungo.banking.dto.ContactDto;
import com.chonfoungo.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.save(contactDto));
    }

    @GetMapping("/")
    public  ResponseEntity<List<ContactDto>> findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{contact-idd}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contact-id") Integer contactId) {
        return ResponseEntity.ok(contactService.findById(contactId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(contactService.findAllByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("contact-id") Integer contactId
    ){
        contactService.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}

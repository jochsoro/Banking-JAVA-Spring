package com.chonfoungo.banking.controllers;


import com.chonfoungo.banking.dto.AddressDto;
import com.chonfoungo.banking.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto) {
      return ResponseEntity.ok(addressService.save(addressDto));
    }

    @GetMapping("/")
    public  ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{address-idd}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address-id") Integer addressId) {
        return ResponseEntity.ok(addressService.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("address-id") Integer addressId
    ){
        addressService.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}

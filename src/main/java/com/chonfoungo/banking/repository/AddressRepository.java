package com.chonfoungo.banking.repository;

import com.chonfoungo.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}

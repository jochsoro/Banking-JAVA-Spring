package com.chonfoungo.banking.repository;

import com.chonfoungo.banking.dto.ContactDto;
import com.chonfoungo.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}

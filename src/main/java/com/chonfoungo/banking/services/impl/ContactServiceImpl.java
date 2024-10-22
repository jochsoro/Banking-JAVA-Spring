package com.chonfoungo.banking.services.impl;

import com.chonfoungo.banking.dto.AddressDto;
import com.chonfoungo.banking.dto.ContactDto;
import com.chonfoungo.banking.models.Contact;
import com.chonfoungo.banking.repository.ContactRepository;
import com.chonfoungo.banking.services.ContactService;
import com.chonfoungo.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;


    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return contactRepository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map((ContactDto::fromEntity))
                .collect(Collectors.toList());

    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No contact found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        contactRepository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return contactRepository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}

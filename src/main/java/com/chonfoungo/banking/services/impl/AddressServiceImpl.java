package com.chonfoungo.banking.services.impl;

import com.chonfoungo.banking.dto.AddressDto;
import com.chonfoungo.banking.models.Address;
import com.chonfoungo.banking.repository.AddressRepository;
import com.chonfoungo.banking.services.AddressService;
import com.chonfoungo.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private ObjectsValidator<AddressDto> validator;




    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        addressRepository.deleteById(id);
    }
}

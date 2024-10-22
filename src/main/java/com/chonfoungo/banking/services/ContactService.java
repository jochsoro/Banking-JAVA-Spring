package com.chonfoungo.banking.services;

import com.chonfoungo.banking.dto.AddressDto;
import com.chonfoungo.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{

    List<ContactDto> findAllByUserId(Integer userId);
}

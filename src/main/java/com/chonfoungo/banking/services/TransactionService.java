package com.chonfoungo.banking.services;

import com.chonfoungo.banking.dto.ContactDto;
import com.chonfoungo.banking.dto.TransactionDto;

import java.util.List;

public interface TransactionService  extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}

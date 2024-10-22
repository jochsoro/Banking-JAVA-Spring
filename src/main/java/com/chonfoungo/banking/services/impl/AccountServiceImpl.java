package com.chonfoungo.banking.services.impl;

import com.chonfoungo.banking.dto.AccountDto;
import com.chonfoungo.banking.exceptions.OperationNonPermitedException;
import com.chonfoungo.banking.models.Account;
import com.chonfoungo.banking.repository.AccountRepository;
import com.chonfoungo.banking.services.AccountService;
import com.chonfoungo.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {

        // block account update -> iban cannot be changed
       /* if(dto.getId() != null) {
            throw new OperationNonPermitedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);

        // vérifier si l'utilisateur a déjà un compte bancaire ou non
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount) {
            throw new OperationNonPermitedException(
                    "The selected account already exists in the database",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // generate random IBAN when creating account else do not update the IBAN
        if(dto.getId() != null){
            account.setIban(generateRandomIban());
        }

        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete account
        accountRepository.deleteById(id);
    }

    private String generateRandomIban(){
        // todo generate iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        // check if the iban already exists
       boolean ibanExists =   accountRepository.findByIban(iban).isPresent();

        // if exists - > generate new random iban
        if(ibanExists){
            generateRandomIban();
        }

        // if not exists -> return generated iban
        return iban;
    }
}

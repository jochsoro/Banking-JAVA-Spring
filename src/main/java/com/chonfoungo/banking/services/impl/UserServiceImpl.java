package com.chonfoungo.banking.services.impl;

import com.chonfoungo.banking.dto.AccountDto;
import com.chonfoungo.banking.dto.UserDto;
import com.chonfoungo.banking.models.Account;
import com.chonfoungo.banking.models.User;
import com.chonfoungo.banking.repository.AccountRepository;
import com.chonfoungo.banking.repository.UserRepository;
import com.chonfoungo.banking.services.AccountService;
import com.chonfoungo.banking.services.UserService;
import com.chonfoungo.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public List<UserDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User not found with the provided ID : " +id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        userRepository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with the provided ID : " +id));
        user.setActive(true);

        //create a bank account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with the provided ID : " +id));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}

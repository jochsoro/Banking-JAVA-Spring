package com.chonfoungo.banking.services;

import com.chonfoungo.banking.dto.UserDto;


public interface UserService extends AbstractService<UserDto> {

   Integer validateAccount(Integer id);
   Integer invalidateAccount(Integer id);
}

package com.chonfoungo.banking.repository;

import com.chonfoungo.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

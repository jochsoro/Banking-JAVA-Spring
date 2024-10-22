package com.chonfoungo.banking.repository;

import com.chonfoungo.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // select * from _user where firstname = 'Chonfoungo'
    List<User> findAllByFirstName(String firstName);

    // select * from _user where firstname like 'Chonfoungo'
    List<User> findAllByFirstNameContaining(String firstName);

    // select * from _user where firstname ilike '%chonfoungo%'
    List<User> findAllByFirstNameContainingIgnoreCase(String firstName);

    // select * from _user u inner join account a on u.id = a.id_user and a.iban = 'ABC123458JJH'
    List<User> findAllByAccountIban(String accountIban);

    // select * from _user_user whre firstName = '%Chonfoungo%' and email = 'joch.soro@gmail.com'
   User findAllByFirstNameContainingIgnoreCaseAndEmail(String firstName, String email);

/*   @Query("from _user where firstName = :firstName")
    List<User> searchByFirstName(String firstName);*/
    @Query("from User where firstName = :fn")
    List<User> searchByFirstName(@Param("fn") String firstName);

    @Query("from User where firstName = '%:firstName%'")
    List<User> searchByFirstNameContaining(String firstName);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);

    @Query(value = "select * from _user u inner join account a on u.id = a.id_user where a.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(String iban);
}

package com.chonfoungo.banking.repository;

import com.chonfoungo.banking.dto.TransactionDto;
import com.chonfoungo.banking.models.Contact;
import com.chonfoungo.banking.models.Transaction;
import com.chonfoungo.banking.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction  t where t.user.id = :userId")
    BigDecimal findAccountBalance(Integer userId);

    @Query("select max(abs(t.amount) ) as amount from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.creationDate, sum(t.amount) from Transaction t where t.user.id = :userId and t.creationDate between :start and :end group by t.creationDate")
    Map<LocalDate, BigDecimal> findSumTransactionsByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}

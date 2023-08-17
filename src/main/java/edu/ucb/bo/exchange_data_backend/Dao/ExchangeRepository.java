package edu.ucb.bo.exchange_data_backend.Dao;

import edu.ucb.bo.exchange_data_backend.Entity.Exchange;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    Exchange findExchangeById(Long id);

    @Modifying
    @Query("UPDATE Exchange SET cufrom = :cufrom, cuto = :cuto, amount = :amount WHERE id = :id")
    @Transactional
    void updateExchangeById(@Param("id") Long id, @Param("cufrom") String cufrom, @Param("cuto") String cuto, @Param("amount") BigDecimal amount);
}

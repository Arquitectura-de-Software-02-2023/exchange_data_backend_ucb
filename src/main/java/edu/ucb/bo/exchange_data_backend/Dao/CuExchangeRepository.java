package edu.ucb.bo.exchange_data_backend.Dao;

import edu.ucb.bo.exchange_data_backend.Entity.CuExchange;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface CuExchangeRepository extends JpaRepository<CuExchange, Integer>{
    CuExchange findExchangeById(Long id);

    @Modifying
    @Query("UPDATE CuExchange SET exFrom = :cufrom, exTo = :cuto, amount = :amount, date = :currentDate, result = :result WHERE id = :id")
    @Transactional
    void updateExchangeById(@Param("id") Long id, @Param("cufrom") String cufrom, @Param("cuto") String cuto, @Param("amount") BigDecimal amount, @Param("currentDate") Date currentDate, @Param("result") BigDecimal result);
}

package edu.ucb.bo.exchange_data_backend.Dao;

import edu.ucb.bo.exchange_data_backend.Entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    Exchange findExchangeById(Long id);
}

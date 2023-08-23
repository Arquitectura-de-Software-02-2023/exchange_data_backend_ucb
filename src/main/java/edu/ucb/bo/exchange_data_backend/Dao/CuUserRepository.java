package edu.ucb.bo.exchange_data_backend.Dao;

import edu.ucb.bo.exchange_data_backend.Entity.CuUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuUserRepository extends JpaRepository<CuUser, Integer> {
    CuUser findCuUserById(Integer id);
}

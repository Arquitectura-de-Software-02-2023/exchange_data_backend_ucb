package edu.ucb.bo.exchange_data_backend.State;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.CurrencyDto;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

public interface CurrencyState {
    ResponseDto handleExchangeRequest(String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException;
    ResponseDto handleUpdateRequest(Long id, String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException;
    ResponseDto handleDeleteRequest(Integer id, Authentication authentication);
    ResponseDto handleGetAllRequest(Authentication authentication);
}




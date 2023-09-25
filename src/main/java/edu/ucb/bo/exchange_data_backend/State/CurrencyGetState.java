package edu.ucb.bo.exchange_data_backend.State;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

public class CurrencyGetState implements CurrencyState{

    CurrencyBl currencyBl;

    public CurrencyGetState(CurrencyBl currencyBl) {
        this.currencyBl = currencyBl;
    }

    @Override
    public ResponseDto handleExchangeRequest(String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        return new CurrencyExchangeState(currencyBl).handleExchangeRequest(to, from, amount, authentication);
    }

    @Override
    public ResponseDto handleUpdateRequest(Long id, String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        return new CurrencyUpdateState(currencyBl).handleUpdateRequest(id, to, from, amount, authentication);
    }

    @Override
    public ResponseDto handleDeleteRequest(Integer id, Authentication authentication) {
        return new CurrencyDeleteState(currencyBl).handleDeleteRequest(id, authentication);
    }

    @Override
    public ResponseDto handleGetAllRequest(Authentication authentication) {
        return currencyBl.getAllExchange();
    }
}

package edu.ucb.bo.exchange_data_backend.State;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

public class CurrencyUpdateState implements CurrencyState{
    private CurrencyBl currencyBl;

    public CurrencyUpdateState(CurrencyBl currencyBl) {
        this.currencyBl = currencyBl;
    }


    @Override
    public ResponseDto handleExchangeRequest(String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        System.out.println("You are CurrencyUpdateState handleExchangeRequest");
        return new CurrencyExchangeState(currencyBl).handleExchangeRequest(to, from, amount, authentication);
    }

    @Override
    public ResponseDto handleUpdateRequest(Long id, String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        System.out.println("You are CurrencyUpdateState handleUpdateRequest");
        ResponseDto responseDto = currencyBl.updateExchange(id, to, from, amount);
        return responseDto;
    }

    @Override
    public ResponseDto handleDeleteRequest(Integer id, Authentication authentication) {
        System.out.println("You are CurrencyUpdateState handleDeleteRequest");
        return new CurrencyDeleteState(currencyBl).handleDeleteRequest(id, authentication);
    }

    @Override
    public ResponseDto handleGetAllRequest(Authentication authentication) {
        System.out.println("You are CurrencyUpdateState handleGetAllRequest");
        return new CurrencyGetState(currencyBl).handleGetAllRequest(authentication);
    }

}

package edu.ucb.bo.exchange_data_backend.State;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.CurrencyDto;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

public class CurrencyExchangeState implements CurrencyState{

    private CurrencyBl currencyBl;

    public CurrencyExchangeState(CurrencyBl currencyBl) {
        this.currencyBl = currencyBl;
    }

    @Override
    public ResponseDto handleExchangeRequest(String to, String from, BigDecimal amount, Authentication authentication) {
        CurrencyDto currencyDto = currencyBl.callingCurrencyService(to, from, amount);
        return currencyBl.saveExchange(currencyDto);
    }

    @Override
    public ResponseDto handleUpdateRequest(Long id, String to, String from, BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        System.out.println("You are CurrencyExchangeState handleUpdateRequest");
        return new CurrencyUpdateState(currencyBl).handleUpdateRequest(id, to, from, amount, authentication);
    }

    @Override
    public ResponseDto handleDeleteRequest(Integer id, Authentication authentication) {
        System.out.println("You are CurrencyExchangeState handleDeleteRequest");
        return new CurrencyDeleteState(currencyBl).handleDeleteRequest(id, authentication);
    }


    @Override
    public ResponseDto handleGetAllRequest(Authentication authentication) {
        System.out.println("You are CurrencyExchangeState handleGetAllRequest");
        return new CurrencyGetState(currencyBl).handleGetAllRequest(authentication);
    }


}

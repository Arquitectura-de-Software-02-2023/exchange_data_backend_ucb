package edu.ucb.bo.exchange_data_backend.Api;

import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/exchange")
public class CurrencyApi {

    @Autowired
    CurrencyBl currencyBl;

    @PostMapping()
    public ResponseDto exchange(@RequestParam String to, @RequestParam String from, @RequestParam BigDecimal amount){
        ResponseDto responseDto = currencyBl.exchange(to, from, amount);
        return responseDto;
    }

    @GetMapping()
    public ResponseDto getExchange(@RequestParam Long id){
        ResponseDto responseDto = currencyBl.getExchange(id);
        return responseDto;
    }

}

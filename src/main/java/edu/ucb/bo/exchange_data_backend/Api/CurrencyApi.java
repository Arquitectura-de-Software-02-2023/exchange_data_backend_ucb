package edu.ucb.bo.exchange_data_backend.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.CurrencyDto;
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
        CurrencyDto currencyDto = currencyBl.callingCurrencyService(to, from, amount);
        return currencyBl.saveExchange(currencyDto);
    }

    @GetMapping()
    public ResponseDto getExchange(@RequestParam Long id){
        ResponseDto responseDto = currencyBl.getExchange(id);
        return responseDto;
    }

    @PutMapping()
    public ResponseDto updateExchange(@RequestParam Long id, @RequestParam String to, @RequestParam String from, @RequestParam BigDecimal amount) throws JsonProcessingException {
        ResponseDto responseDto = currencyBl.updateExchange(id, to, from, amount);
        return responseDto;
    }




    @DeleteMapping()
    public ResponseDto deleteExchange(@RequestParam Integer id){
        ResponseDto responseDto = currencyBl.deleteExchange(id);
        return responseDto;
    }

    @GetMapping("/all")
    public ResponseDto getAllExchange(){
        ResponseDto responseDto = currencyBl.getAllExchange();
        return responseDto;
    }





}

package edu.ucb.bo.exchange_data_backend.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.CurrencyDto;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin (origins = "*")
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

    @GetMapping("/user/all")
    public ResponseDto<Page<CurrencyDto>> getAllExchangeAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        System.out.println(pageable);
        ResponseDto res = new ResponseDto();
        try{
            Page<CurrencyDto> responseDto = currencyBl.getAllExchangePageable(pageable);
            res.setCode("000");
            res.setResponse(responseDto);
        } catch (Exception e){
            res.setCode("999");
            res.setError("Error");
            res.setResponse(null);
        }
        return res;
    }





}

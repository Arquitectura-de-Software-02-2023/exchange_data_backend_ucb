package edu.ucb.bo.exchange_data_backend.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucb.bo.exchange_data_backend.Bl.CurrencyBl;
import edu.ucb.bo.exchange_data_backend.Dto.CurrencyDto;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import edu.ucb.bo.exchange_data_backend.State.CurrencyExchangeState;
import edu.ucb.bo.exchange_data_backend.State.CurrencyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
//@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/exchange")
public class CurrencyApi {
    private CurrencyState currencyState;



    private final CurrencyBl currencyBl; // Add a final field for CurrencyBl

    @Autowired
    public CurrencyApi(CurrencyBl currencyBl) {
        this.currencyBl = currencyBl;
        currencyState = new CurrencyExchangeState(currencyBl);
    }
    @PostMapping("/new")
    public ResponseDto exchange(@RequestParam String to, @RequestParam String from, @RequestParam BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        return currencyState.handleExchangeRequest(to, from, amount, authentication);
    }

    @GetMapping()
    public ResponseDto getExchange(@RequestParam Long id, Authentication authentication){
        ResponseDto responseDto = currencyBl.getExchange(id);
        return responseDto;
    }

    @PutMapping("/update")
    public ResponseDto updateExchange(@RequestParam Long id, @RequestParam String to, @RequestParam String from, @RequestParam BigDecimal amount, Authentication authentication) throws JsonProcessingException {
        return currencyState.handleUpdateRequest(id, to, from, amount, authentication);
    }

    @DeleteMapping("/delete")
    public ResponseDto deleteExchange(@RequestParam Integer id, Authentication authentication){
        return currencyState.handleDeleteRequest(id, authentication);
    }

    @GetMapping("/all")
    public ResponseDto getAllExchange(Authentication authentication){
        return currencyState.handleGetAllRequest(authentication);
    }

    @GetMapping("/user/all")
    public ResponseDto<Page<CurrencyDto>> getAllExchangeAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, Authentication authentication){
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

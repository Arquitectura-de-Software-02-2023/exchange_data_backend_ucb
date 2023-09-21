package edu.ucb.bo.exchange_data_backend.Bl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucb.bo.exchange_data_backend.Dao.CuExchangeRepository;
import edu.ucb.bo.exchange_data_backend.Dao.CuUserRepository;
import edu.ucb.bo.exchange_data_backend.Dto.CurrencyDto;
import edu.ucb.bo.exchange_data_backend.Dto.RequestDto;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import edu.ucb.bo.exchange_data_backend.Entity.CuExchange;
import edu.ucb.bo.exchange_data_backend.Entity.CuUser;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Service
public class CurrencyBl {
    @Autowired
    private CuExchangeRepository cuExchangeRepository;
    @Autowired
    private CuUserRepository cuUserRepository;

    @Value("${currency.url}")
    private String url;
    @Value("${currency.apiKey}")
    private String apiKey;

    public CurrencyDto callingCurrencyService(String to, String from, BigDecimal amount){
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Calling the currency API");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url+"?from="+from+"&to="+to+"&amount="+amount)
                .addHeader("apiKey", apiKey)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String res = response.body().string();
            if(response.isSuccessful()){
                logger.info("Successful call api");
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(res, CurrencyDto.class);
            }
            throw new RuntimeException("Error calling the currency API");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDto saveExchange(CurrencyDto currencyDto){
        CuExchange exchange = new CuExchange();
        ResponseDto responseDto = new ResponseDto();
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Saving the exchange");
        exchange.setExFrom(currencyDto.getQuery().getFrom());
        exchange.setExTo(currencyDto.getQuery().getTo());
        exchange.setAmount(currencyDto.getQuery().getAmount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            exchange.setDate(dateFormat.parse(currencyDto.getDate()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        exchange.setResult(currencyDto.getResult());
        exchange.setCuUser(cuUserRepository.findCuUserById(1)); //Saves the user by ID
        cuExchangeRepository.save(exchange);
        responseDto.setCode("200");
        responseDto.setResponse(currencyDto);
        return responseDto;
    }

    public ResponseDto getExchange(Long id){
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Getting the exchange");
        ResponseDto res = new ResponseDto();
        CuExchange cuExchange = cuExchangeRepository.findExchangeById(id);
        CurrencyDto currencyDto = new CurrencyDto();
        RequestDto requestDto = new RequestDto();
        requestDto.setFrom(cuExchange.getExFrom());
        requestDto.setTo(cuExchange.getExTo());
        requestDto.setAmount(cuExchange.getAmount());
        currencyDto.setSuccess(true);
        currencyDto.setQuery(requestDto);
        currencyDto.setInfo(currencyDto.getInfo());
        currencyDto.setDate(cuExchange.getDate().toString());
        currencyDto.setResult(cuExchange.getResult());
        res.setCode("200");
        res.setResponse(currencyDto);
        logger.info("Returning the exchange");
        return res;
    }

    public ResponseDto  updateExchange(Long id, String to, String from, BigDecimal amount){
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Updating the exchange");
        ResponseDto res = new ResponseDto();
        res.setCode("200");
        logger.info("Calling the repository");
        logger.info("To" + to);
        logger.info("From" + from);
        logger.info("Amount" + amount);

        CurrencyDto cu = callingCurrencyService(to, from, amount);
        cu.setId(Integer.parseInt(id.toString()));
        cuExchangeRepository.updateExchangeById(id, to, from, amount, new Date(), cu.getResult());
        logger.info("Calling the repository after update");
        res.setResponse(cu);
        return res;
    }

    public ResponseDto deleteExchange(int id){
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Deleting the exchange");
        ResponseDto res = new ResponseDto();
        res.setCode("200");
        logger.info("Calling the repository");
        cuExchangeRepository.deleteById(id);
        logger.info("Calling the repository after delete");
        return res;
    }

    public ResponseDto getAllExchange(){
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Getting all the exchanges");
        ResponseDto res = new ResponseDto();
        res.setCode("200");
        CuUser cuUser = cuUserRepository.findCuUserById(1); //Need the user ID as a parameter
        List<CuExchange> cuExchangeList = cuExchangeRepository.findAllByCuUser(cuUser);
        List<CurrencyDto> currencyDtoList = new ArrayList<>();
        for (CuExchange cuExchange : cuExchangeList) {
            CurrencyDto currencyDto = new CurrencyDto();
            RequestDto requestDto = new RequestDto();
            requestDto.setFrom(cuExchange.getExFrom());
            requestDto.setTo(cuExchange.getExTo());
            requestDto.setAmount(cuExchange.getAmount());
            currencyDto.setId(cuExchange.getId());
            currencyDto.setSuccess(true);
            currencyDto.setQuery(requestDto);
            currencyDto.setInfo(currencyDto.getInfo());
            currencyDto.setDate(cuExchange.getDate().toString());
            currencyDto.setResult(cuExchange.getResult());
            currencyDtoList.add(currencyDto);
        }
        res.setResponse(currencyDtoList);
        logger.info("Returning all the exchanges");
        return res;
    }

    public Page<CurrencyDto> getAllExchangePageable(Pageable pageable){
        Logger logger = Logger.getLogger(CurrencyBl.class.getName());
        logger.info("Getting all the exchanges");
        Page<CuExchange> cuExchangeList = cuExchangeRepository.findAllExchange(pageable);
        logger.info("Returning all the exchanges");
        return cuExchangeList.map(this::convertToDto);
    }

    private CurrencyDto convertToDto(CuExchange cuExchange){
        CurrencyDto currencyDto = new CurrencyDto();
        RequestDto requestDto = new RequestDto();
        requestDto.setFrom(cuExchange.getExFrom());
        requestDto.setTo(cuExchange.getExTo());
        requestDto.setAmount(cuExchange.getAmount());
        currencyDto.setId(cuExchange.getId());
        currencyDto.setSuccess(true);
        currencyDto.setQuery(requestDto);
        currencyDto.setInfo(currencyDto.getInfo());
        currencyDto.setDate(cuExchange.getDate().toString());
        currencyDto.setResult(cuExchange.getResult());
        return currencyDto;
    }


}

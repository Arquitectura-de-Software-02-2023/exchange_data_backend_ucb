package edu.ucb.bo.exchange_data_backend.Bl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucb.bo.exchange_data_backend.Dto.ResponseDto;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;



@Service
public class CurrencyBl {
    @Value("${currency.url}")
    private String url;
    @Value("${currency.apiKey}")
    private String apiKey;

    public ResponseDto exchange (String to, String from, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("An amount cannot be a negative value");
        }
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
                return responseDto(res);
            }
            throw new RuntimeException("Error calling the currency API");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ResponseDto responseDto (String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, ResponseDto.class);
    }


}

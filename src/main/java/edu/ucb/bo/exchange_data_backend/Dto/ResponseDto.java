package edu.ucb.bo.exchange_data_backend.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseDto <T> {
    private String code;
    private T response;
    private String error;

    public ResponseDto() {
    }


    public ResponseDto(String code, T response, String error) {
        this.code = code;
        this.response = response;
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", response=" + response +
                ", error='" + error + '\'' +
                '}';
    }
}

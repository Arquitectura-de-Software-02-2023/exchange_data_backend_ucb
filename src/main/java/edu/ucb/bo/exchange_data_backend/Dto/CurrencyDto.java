package edu.ucb.bo.exchange_data_backend.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {
    private int id;
    private boolean success;
    private RequestDto query;
    private InfoDto info;
    private String date;

    private BigDecimal result;


    public CurrencyDto() {
    }

    public CurrencyDto(int id, boolean success, RequestDto query, InfoDto info, String date, BigDecimal result) {
        this.id = id;
        this.success = success;
        this.query = query;
        this.info = info;
        this.date = date;
        this.result = result;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RequestDto getQuery() {
        return query;
    }

    public void setQuery(RequestDto query) {
        this.query = query;
    }

    public InfoDto getInfo() {
        return info;
    }

    public void setInfo(InfoDto info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "id=" + id +
                ", success=" + success +
                ", query=" + query +
                ", info=" + info +
                ", date='" + date + '\'' +
                ", result=" + result +
                '}';
    }
}

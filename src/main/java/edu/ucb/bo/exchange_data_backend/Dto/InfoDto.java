package edu.ucb.bo.exchange_data_backend.Dto;

import java.math.BigDecimal;

public class InfoDto {
    private String timestamp;
    private BigDecimal rate;

    public InfoDto() {
    }

    public InfoDto(String timestamp, BigDecimal rate) {
        this.timestamp = timestamp;
        this.rate = rate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "InfoDto{" +
                "timestamp='" + timestamp + '\'' +
                ", rate=" + rate +
                '}';
    }
}

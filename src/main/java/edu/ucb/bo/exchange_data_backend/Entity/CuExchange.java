package edu.ucb.bo.exchange_data_backend.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cu_exchange")
public class CuExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cu_user_id", nullable = false)
    private CuUser cuUser;

    private String exFrom;
    private String exTo;
    private BigDecimal amount;
    private Date date;
    private BigDecimal result;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CuUser getCuUser() {
        return cuUser;
    }

    public void setCuUser(CuUser cuUser) {
        this.cuUser = cuUser;
    }

    public String getExFrom() {
        return exFrom;
    }

    public void setExFrom(String exFrom) {
        this.exFrom = exFrom;
    }

    public String getExTo() {
        return exTo;
    }

    public void setExTo(String exTo) {
        this.exTo = exTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return "CuExchange{" +
                "id=" + id +
                ", cuUser=" + cuUser +
                ", exFrom='" + exFrom + '\'' +
                ", exTo='" + exTo + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", result=" + result +
                '}';
    }
}
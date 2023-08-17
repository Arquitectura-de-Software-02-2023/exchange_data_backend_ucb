package edu.ucb.bo.exchange_data_backend.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cufrom", length = 5)
    private String cufrom;

    @Column(name = "cuto", length = 5)
    private String cuto;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "result")
    private BigDecimal result;

    public Exchange() {
    }

    public Exchange(Long id, String cufrom, String cuto, BigDecimal amount, Date date, BigDecimal result) {
        this.id = id;
        this.cufrom = cufrom;
        this.cuto = cuto;
        this.amount = amount;
        this.date = date;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCufrom() {
        return cufrom;
    }

    public void setCufrom(String cufrom) {
        this.cufrom = cufrom;
    }

    public String getCuto() {
        return cuto;
    }

    public void setCuto(String cuto) {
        this.cuto = cuto;
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
        return "Exchange{" +
                "id=" + id +
                ", cufrom='" + cufrom + '\'' +
                ", cuto='" + cuto + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", result=" + result +
                '}';
    }
}

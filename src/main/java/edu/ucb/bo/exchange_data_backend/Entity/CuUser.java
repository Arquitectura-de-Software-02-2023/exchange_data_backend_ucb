package edu.ucb.bo.exchange_data_backend.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cu_user")
public class CuUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String secret;

    @OneToMany(mappedBy = "cuUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CuExchange> exchanges;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public List<CuExchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<CuExchange> exchanges) {
        this.exchanges = exchanges;
    }
}
package hu.imregerman.investmentportfoliomanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "stocks")
public class Stock implements Serializable {

    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String symbol;

    private String name;

    private Double price;

    private String exchange;

    @Column(name = "exchange_shortname")
    private String exchangeShortName;

    private String type;

    @Column(name = "last_update")
    private ZonedDateTime lastUpdate;

    @OneToMany(mappedBy = "stock")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "stock")
    private Set<Dividend> dividends;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchangeShortName() {
        return exchangeShortName;
    }

    public void setExchangeShortName(String exchangeShortName) {
        this.exchangeShortName = exchangeShortName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Dividend> getDividends() {
        return dividends;
    }

    public void setDividends(Set<Dividend> dividends) {
        this.dividends = dividends;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", exchange='" + exchange + '\'' +
                ", exchangeShortName='" + exchangeShortName + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", transactions=" + transactions +
                '}';
    }
}

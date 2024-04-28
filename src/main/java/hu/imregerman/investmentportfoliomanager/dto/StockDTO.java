package hu.imregerman.investmentportfoliomanager.dto;

import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

@ToString
public class StockDTO {

    private UUID id;
    private String symbol;
    private String name;
    private String exchange;
    private String type;
    private Double price;
    private ZonedDateTime lastUpdate;

    public StockDTO(UUID id, String symbol, String name, String exchange, String type, Double price, ZonedDateTime lastUpdate) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.exchange = exchange;
        this.type = type;
        this.price = price;
        this.lastUpdate = lastUpdate;
    }

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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

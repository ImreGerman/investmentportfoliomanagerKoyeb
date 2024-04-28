package hu.imregerman.investmentportfoliomanager.dto;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class SymbolAndPriceDTO implements Serializable {

    private String symbol;
    private Double price;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

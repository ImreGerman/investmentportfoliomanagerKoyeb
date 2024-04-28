package hu.imregerman.investmentportfoliomanager.dto;

import java.util.Objects;
import java.util.UUID;

public class UserStockDTO {

    private UUID id;
    private String symbol;
    private String stockName;
    private Double averageCostPrice;
    private Double sumStockQuantity;
    private Double sumTransactionAmount;
    private Double sumTransactionAmountInForeignCurrency;
    private Double sumDividendPrice;
    private Double sumRemainingStockQuantity;
    private Double sumSoldTransactionAmountInForeignCurrency;

    public UserStockDTO(UUID id, String symbol, String stockName, Double averageCostPrice, Double sumStockQuantity, Double sumTransactionAmount,
                        Double sumTransactionAmountInForeignCurrency, Double sumDividendPrice, Double sumRemainingStockQuantity, Double sumSoldTransactionAmountInForeignCurrency) {
        this.id = id;
        this.symbol = symbol;
        this.stockName = stockName;
        this.averageCostPrice = averageCostPrice;
        this.sumStockQuantity = sumStockQuantity;
        this.sumTransactionAmount = sumTransactionAmount;
        this.sumTransactionAmountInForeignCurrency = sumTransactionAmountInForeignCurrency;
        this.sumDividendPrice = sumDividendPrice;
        this.sumRemainingStockQuantity = sumRemainingStockQuantity;
        this.sumSoldTransactionAmountInForeignCurrency = sumSoldTransactionAmountInForeignCurrency;
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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getAverageCostPrice() {
        return averageCostPrice;
    }

    public void setAverageCostPrice(Double averageCostPrice) {
        this.averageCostPrice = averageCostPrice;
    }

    public Double getSumStockQuantity() {
        return sumStockQuantity;
    }

    public void setSumStockQuantity(Double sumStockQuantity) {
        this.sumStockQuantity = sumStockQuantity;
    }

    public Double getSumTransactionAmount() {
        return sumTransactionAmount;
    }

    public void setSumTransactionAmount(Double sumTransactionAmount) {
        this.sumTransactionAmount = sumTransactionAmount;
    }

    public Double getSumTransactionAmountInForeignCurrency() {
        return sumTransactionAmountInForeignCurrency;
    }

    public void setSumTransactionAmountInForeignCurrency(Double sumTransactionAmountInForeignCurrency) {
        this.sumTransactionAmountInForeignCurrency = sumTransactionAmountInForeignCurrency;
    }

    public Double getSumDividendPrice() {
        return Objects.requireNonNullElse(sumDividendPrice, 0.0);
    }

    public void setSumDividendPrice(Double sumDividendPrice) {
        this.sumDividendPrice = sumDividendPrice;
    }

    public Double getSumRemainingStockQuantity() {
        return sumRemainingStockQuantity;
    }

    public void setSumRemainingStockQuantity(Double sumRemainingStockQuantity) {
        this.sumRemainingStockQuantity = sumRemainingStockQuantity;
    }

    public Double getSumSoldTransactionAmountInForeignCurrency() {
        return sumSoldTransactionAmountInForeignCurrency;
    }

    public void setSumSoldTransactionAmountInForeignCurrency(Double sumSoldTransactionAmountInForeignCurrency) {
        this.sumSoldTransactionAmountInForeignCurrency = sumSoldTransactionAmountInForeignCurrency;
    }

    @Override
    public String toString() {
        return "UserStockDTO{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", stockName='" + stockName + '\'' +
                ", averageCostPrice=" + averageCostPrice +
                ", sumStockQuantity=" + sumStockQuantity +
                ", sumTransactionAmount=" + sumTransactionAmount +
                ", sumTransactionAmountInForeignCurrency=" + sumTransactionAmountInForeignCurrency +
                ", sumDividendPrice=" + sumDividendPrice +
                ", sumRemainingStockQuantity=" + sumRemainingStockQuantity +
                ", sumSoldTransactionAmountInForeignCurrency=" + sumSoldTransactionAmountInForeignCurrency +
                '}';
    }
}

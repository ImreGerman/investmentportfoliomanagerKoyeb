package hu.imregerman.investmentportfoliomanager.dto;

import java.util.UUID;

public class ClosedUserStockDTO {

    private UUID id;
    private String symbol;
    private String stockName;
    private Double averageBoughtCostPrice;
    private Double averageSoldCostPrice;
    private Double sumStockQuantity;
    private Double profitInForeignCurrency;
    private Double roi;
    private Double sumDividendPrice;
    private Double roiWithDividend;
    private Double sumBoughtTransactionAmount;

    public ClosedUserStockDTO(UUID id, String symbol, String stockName, Double averageBoughtCostPrice, Double averageSoldCostPrice, Double sumStockQuantity,
                              Double profitInForeignCurrency, Double roi, Double sumDividendPrice, Double roiWithDividend, Double sumBoughtTransactionAmount) {
        this.id = id;
        this.symbol = symbol;
        this.stockName = stockName;
        this.averageBoughtCostPrice = averageBoughtCostPrice;
        this.averageSoldCostPrice = averageSoldCostPrice;
        this.sumStockQuantity = sumStockQuantity;
        this.profitInForeignCurrency = profitInForeignCurrency;
        this.roi = roi;
        this.sumDividendPrice = sumDividendPrice;
        this.roiWithDividend = roiWithDividend;
        this.sumBoughtTransactionAmount = sumBoughtTransactionAmount;
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

    public Double getAverageBoughtCostPrice() {
        return averageBoughtCostPrice;
    }

    public void setAverageBoughtCostPrice(Double averageBoughtCostPrice) {
        this.averageBoughtCostPrice = averageBoughtCostPrice;
    }

    public Double getAverageSoldCostPrice() {
        return averageSoldCostPrice;
    }

    public void setAverageSoldCostPrice(Double averageSoldCostPrice) {
        this.averageSoldCostPrice = averageSoldCostPrice;
    }

    public Double getSumStockQuantity() {
        return sumStockQuantity;
    }

    public void setSumStockQuantity(Double sumStockQuantity) {
        this.sumStockQuantity = sumStockQuantity;
    }

    public Double getProfitInForeignCurrency() {
        return profitInForeignCurrency;
    }

    public void setProfitInForeignCurrency(Double profitInForeignCurrency) {
        this.profitInForeignCurrency = profitInForeignCurrency;
    }

    public Double getRoi() {
        return roi;
    }

    public void setRoi(Double roi) {
        this.roi = roi;
    }

    public Double getSumDividendPrice() {
        return sumDividendPrice;
    }

    public void setSumDividendPrice(Double sumDividendPrice) {
        this.sumDividendPrice = sumDividendPrice;
    }

    public Double getRoiWithDividend() {
        return roiWithDividend;
    }

    public void setRoiWithDividend(Double roiWithDividend) {
        this.roiWithDividend = roiWithDividend;
    }

    public Double getSumBoughtTransactionAmount() {
        return sumBoughtTransactionAmount;
    }

    public void setSumBoughtTransactionAmount(Double sumBoughtTransactionAmount) {
        this.sumBoughtTransactionAmount = sumBoughtTransactionAmount;
    }

    @Override
    public String toString() {
        return "ClosedUserStockDTO{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", stockName='" + stockName + '\'' +
                ", averageBoughtCostPrice=" + averageBoughtCostPrice +
                ", averageSoldCostPrice=" + averageSoldCostPrice +
                ", sumStockQuantity=" + sumStockQuantity +
                ", profitInForeignCurrency=" + profitInForeignCurrency +
                ", roi=" + roi +
                ", sumDividendPrice=" + sumDividendPrice +
                ", roiWithDividend=" + roiWithDividend +
                ", sumBoughtTransactionAmount=" + sumBoughtTransactionAmount +
                '}';
    }
}

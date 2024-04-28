package hu.imregerman.investmentportfoliomanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @UuidGenerator()
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NotNull(message = "Transaction date cannot be NULL")
    @PastOrPresent(message = "Transaction date cannot be in the future")
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "stock_name")
    private String stockName;

    @NotNull(message = "Transaction amount cannot be NULL")
    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @NotNull(message = "Stock price cannot be NULL")
    @Positive(message = "Stock price must be positive")
    @Column(name = "stock_price")
    private Double stockPrice;

    @NotNull(message = "Stock quantity cannot be NULL")
    @Column(name = "stock_quantity")
    private Double stockQuantity;

    @Column(name = "transaction_fee")
    private Double transactionFee;

    @Column(name = "exchange_name")
    private String exchangeName;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "transaction_amount_in_foreign_currency")
    private Double transactionAmountInForeignCurrency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "transaction_group_closed_id")
    private String transactionGroupClosedId;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getTransactionAmountInForeignCurrency() {
        return transactionAmountInForeignCurrency;
    }

    public void setTransactionAmountInForeignCurrency(Double transactionAmountInForeignCurrency) {
        this.transactionAmountInForeignCurrency = transactionAmountInForeignCurrency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getTransactionGroupClosedId() {
        return transactionGroupClosedId;
    }

    public void setTransactionGroupClosedId(String transactionGroupClosedId) {
        this.transactionGroupClosedId = transactionGroupClosedId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", transactionType=" + transactionType +
                ", stockName='" + stockName + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", stockPrice=" + stockPrice +
                ", stockQuantity=" + stockQuantity +
                ", transactionFee=" + transactionFee +
                ", exchangeName='" + exchangeName + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", transactionAmountInForeignCurrency=" + transactionAmountInForeignCurrency +
                ", user=" + user.getUserName() + user.getEmail() +
                ", stock=" + stock.getSymbol() + stock.getPrice() + stock.getLastUpdate() +
                '}';
    }
}

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
@Table(name = "dividends")
public class Dividend {

    @Id
    @UuidGenerator()
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NotNull(message = "Stock price cannot be NULL")
    @Positive(message = "Stock price must be positive")
    @Column(name = "dividend_price")
    private Double dividendPrice;

    @NotNull(message = "Transaction date cannot be NULL")
    @PastOrPresent(message = "Transaction date cannot be in the future")
    @Column(name = "dividend_date")
    private LocalDate dividendDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "dividend_group_closed_id")
    private String dividendGroupClosedId;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getDividendPrice() {
        return dividendPrice;
    }

    public void setDividendPrice(Double dividendPrice) {
        this.dividendPrice = dividendPrice;
    }

    public LocalDate getDividendDate() {
        return dividendDate;
    }

    public void setDividendDate(LocalDate dividendDate) {
        this.dividendDate = dividendDate;
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

    public String getDividendGroupClosedId() {
        return dividendGroupClosedId;
    }

    public void setDividendGroupClosedId(String dividendGroupClosedId) {
        this.dividendGroupClosedId = dividendGroupClosedId;
    }

    @Override
    public String toString() {
        return "Dividend{" +
                "id=" + id +
                ", dividendPrice=" + dividendPrice +
                ", dividendDate=" + dividendDate +
                ", user=" + user +
                ", stock=" + stock +
                '}';
    }
}

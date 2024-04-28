package hu.imregerman.investmentportfoliomanager.repository;

import hu.imregerman.investmentportfoliomanager.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public interface StockExternalApiRepository extends JpaRepository<Stock, UUID> {

    @Modifying
    @Query("UPDATE Stock s SET s.price = :price, s.lastUpdate = :lastUpdate WHERE s.symbol = :symbol")
    int updateStockPriceBySymbol(Double price, ZonedDateTime lastUpdate, String symbol);


}

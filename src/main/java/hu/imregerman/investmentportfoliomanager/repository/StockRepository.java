package hu.imregerman.investmentportfoliomanager.repository;

import hu.imregerman.investmentportfoliomanager.dto.ClosedUserStockDTO;
import hu.imregerman.investmentportfoliomanager.dto.StockDTO;
import hu.imregerman.investmentportfoliomanager.dto.UserStockDTO;
import hu.imregerman.investmentportfoliomanager.model.Dividend;
import hu.imregerman.investmentportfoliomanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Transaction, UUID> {

    @Query("""
            SELECT new hu.imregerman.investmentportfoliomanager.dto.UserStockDTO(
                s.id,
                s.symbol,
                s.name,
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END) / SUM(CASE WHEN t.transactionType = 'BUY' THEN t.stockQuantity END),
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.stockQuantity END),
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmount END),
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END),
                d.sumDividendPrice,
                SUM(t.stockQuantity),
                SUM(CASE WHEN t.transactionType = 'SELL' THEN ABS(transactionAmountInForeignCurrency) END))
            FROM Transaction t
                JOIN t.stock s
                JOIN t.user u
                LEFT JOIN (
                    SELECT d.stock.id AS stockId, SUM(d.dividendPrice) AS sumDividendPrice
                    FROM Dividend d
                    WHERE d.user.userName = :userName
                    GROUP BY d.stock.id
                ) d ON d.stockId = s.id
            WHERE u.userName = :userName
              AND t.transactionGroupClosedId IS NULL
            GROUP BY s.id, d.sumDividendPrice
            """)
    List<UserStockDTO> findUserAllStocks(String userName);

    @Query("""
            SELECT t
            FROM Transaction t
                JOIN t.user u
            WHERE u.userName = :userName
              AND t.stock.id = :stockId
            """)
    List<Transaction> findUserTransactionsByStockId(UUID stockId, String userName);

    @Query("""
            SELECT new hu.imregerman.investmentportfoliomanager.dto.StockDTO(
                   s.id,
                   s.symbol,
                   s.name,
                   s.exchange,
                   s.type,
                   s.price,
                   s.lastUpdate)
            FROM Stock s
            WHERE s.lastUpdate IS NOT NULL
              AND (s.name LIKE CONCAT('%', :keyword, '%') OR s.symbol LIKE CONCAT('%', :keyword, '%'))
            """)
    List<StockDTO> findStockByKeyword(String keyword);

    @Query("""
            SELECT d
            FROM Dividend d
                JOIN d.user u
            WHERE u.userName = :userName
              AND d.stock.id = :stockId
            """)
    Optional<List<Dividend>> findUserDividendsByStockId(UUID stockId, String userName);

    @Query("""
            SELECT new hu.imregerman.investmentportfoliomanager.dto.ClosedUserStockDTO(
                s.id,
                s.symbol,
                s.name,
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END) / SUM(CASE WHEN t.transactionType = 'BUY' THEN t.stockQuantity END),
                SUM(CASE WHEN t.transactionType = 'SELL' THEN t.transactionAmountInForeignCurrency END) / SUM(CASE WHEN t.transactionType = 'SELL' THEN t.stockQuantity END),
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.stockQuantity END),
                SUM(t.transactionAmountInForeignCurrency) * -1,
                (sum(CASE WHEN t.transactionType = 'SELL' THEN ABS(t.transactionAmountInForeignCurrency) END) -
                    sum(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END)) /
                    sum(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END) * 100,
                COALESCE(d.sumDividendPrice, 0),
                (sum(CASE WHEN t.transactionType = 'SELL' THEN ABS(t.transactionAmountInForeignCurrency) END) -
                    sum(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END) + COALESCE(d.sumDividendPrice, 0)) /
                    sum(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END) * 100,
                SUM(CASE WHEN t.transactionType = 'BUY' THEN t.transactionAmountInForeignCurrency END))
            FROM Transaction t
                JOIN t.stock s
                JOIN t.user u
                LEFT JOIN (
                    SELECT d.stock.id AS stockId, SUM(d.dividendPrice) AS sumDividendPrice
                    FROM Dividend d
                    WHERE d.user.userName = :userName
                    GROUP BY d.stock.id
                ) d ON d.stockId = s.id
            WHERE u.userName = :userName
              AND t.transactionGroupClosedId IS NOT NULL
            GROUP BY t.transactionGroupClosedId, s.id, d.sumDividendPrice
            """)
    List<ClosedUserStockDTO> findUserAllClosedStocks(String userName);
}
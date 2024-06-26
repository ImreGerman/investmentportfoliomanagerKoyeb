package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.dto.ClosedUserStockDTO;
import hu.imregerman.investmentportfoliomanager.dto.StockDTO;
import hu.imregerman.investmentportfoliomanager.dto.UserStockDTO;
import hu.imregerman.investmentportfoliomanager.model.Dividend;
import hu.imregerman.investmentportfoliomanager.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockService {

    void saveTransaction(Transaction transaction);

    List<UserStockDTO> getUserAllStocks(User user);

    List<Transaction> getStockAllTransactions(UUID stockID, User user);

    Transaction getTransactionById(String transactionId);

    void removeTransaction(String transactionId);

    Page<StockDTO> getAllStocks(int pageNumber);

    List<StockDTO> getStockByKeyword(String keyword);

    Optional<List<Dividend>> getStockAllDividends(UUID stockID, User user);

    void closeStockAndRelatedDividends(List<UserStockDTO> userAllStocks, String generatedRandomUUID, User user);

    List<ClosedUserStockDTO> getUserAllClosedStocks(User user);
}

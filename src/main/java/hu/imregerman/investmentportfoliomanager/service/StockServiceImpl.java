package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.dto.ClosedUserStockDTO;
import hu.imregerman.investmentportfoliomanager.dto.StockDTO;
import hu.imregerman.investmentportfoliomanager.dto.UserStockDTO;
import hu.imregerman.investmentportfoliomanager.model.Dividend;
import hu.imregerman.investmentportfoliomanager.model.Transaction;
import hu.imregerman.investmentportfoliomanager.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Override
    public void saveTransaction(Transaction transaction) {
        stockRepository.save(transaction);
    }

    @Override
    public List<UserStockDTO> getUserAllStocks(User user) {
        List<UserStockDTO> userAllStocks = stockRepository.findUserAllStocks(user.getUsername());

        for (UserStockDTO stock : userAllStocks) {
            if (stock.getSumSoldTransactionAmountInForeignCurrency() == null) {
                stock.setSumSoldTransactionAmountInForeignCurrency(0.0);
            }
        }
        log.info(userAllStocks.toString());
        return userAllStocks;
    }

    @Override
    public List<Transaction> getStockAllTransactions(UUID stockId, User user) {
        return stockRepository.findUserTransactionsByStockId(stockId, user.getUsername());
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        Optional<Transaction> transaction = stockRepository.findById(UUID.fromString(transactionId));
            return transaction.get();
    }

    @Override
    public void removeTransaction(String transactionId) {
        stockRepository.deleteById(UUID.fromString(transactionId));
    }

    @Override
    public List<StockDTO> getAllStock(String keyword) {
        return stockRepository.findStockByKeyword(keyword);
    }

    @Override
    public Optional<List<Dividend>> getStockAllDividends(UUID stockId, User user) {
        return stockRepository.findUserDividendsByStockId(stockId, user.getUsername());
    }

    @Override
    public List<ClosedUserStockDTO> getUserAllClosedStocks(User user) {
        return stockRepository.findUserAllClosedStocks(user.getUsername());
    }
}

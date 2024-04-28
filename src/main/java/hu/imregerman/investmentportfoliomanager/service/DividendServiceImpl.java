package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.model.Dividend;
import hu.imregerman.investmentportfoliomanager.repository.DividendRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DividendServiceImpl implements DividendService {

    private DividendRepository dividendRepository;

    public DividendServiceImpl(DividendRepository dividendRepository) {
        this.dividendRepository = dividendRepository;
    }

    @Override
    public void saveDividend(Dividend dividend) {
        dividendRepository.save(dividend);
    }

    @Override
    public Dividend getDividendById(String dividendId) {
        Optional<Dividend> dividend = dividendRepository.findById(UUID.fromString(dividendId));
        return dividend.get();
    }

    @Override
    public void removeDividend(String dividendId) {
        dividendRepository.deleteById(UUID.fromString(dividendId));
    }

    @Override
    public void saveAllStockDividends(List<Dividend> dividends) {
        dividendRepository.saveAll(dividends);
    }
}

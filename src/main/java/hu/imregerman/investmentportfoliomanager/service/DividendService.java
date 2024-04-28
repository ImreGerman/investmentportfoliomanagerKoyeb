package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.model.Dividend;

import java.util.List;

public interface DividendService {

    void saveDividend(Dividend dividend);

    Dividend getDividendById(String dividendId);

    void removeDividend(String dividendId);

    void saveAllStockDividends(List<Dividend> dividends);
}

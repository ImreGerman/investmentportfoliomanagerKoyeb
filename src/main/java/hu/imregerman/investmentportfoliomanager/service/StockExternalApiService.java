package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.dto.SymbolAndPriceListDTO;
import hu.imregerman.investmentportfoliomanager.model.Stock;

import java.io.IOException;

public interface StockExternalApiService {

    Stock[] getStockList();

    void saveStockList() throws IOException;

    SymbolAndPriceListDTO getStockPriceList();

    int updateStockPriceList();

    String getApiKey();
}

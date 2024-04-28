package hu.imregerman.investmentportfoliomanager.dto;

import java.util.List;

public class SymbolAndPriceListDTO {

    private List<SymbolAndPriceDTO> stockList;

    public List<SymbolAndPriceDTO> getStockList() {
        return stockList;
    }

    public void setStockList(List<SymbolAndPriceDTO> stockList) {
        this.stockList = stockList;
    }
}

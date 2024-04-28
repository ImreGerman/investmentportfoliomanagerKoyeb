package hu.imregerman.investmentportfoliomanager.controller;

import hu.imregerman.investmentportfoliomanager.dto.SymbolAndPriceListDTO;
import hu.imregerman.investmentportfoliomanager.model.Stock;
import hu.imregerman.investmentportfoliomanager.service.StockExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class StockExternalApiController {

    private StockExternalApiService stockExternalApiService;

    public StockExternalApiController(StockExternalApiService stockExternalApiService) {
        this.stockExternalApiService = stockExternalApiService;
    }

    @GetMapping("/getStockList")
    public Stock[] getStockList() {
        return stockExternalApiService.getStockList();
    }

    // TODO exception handling and response to the endpoint when finished the process
    @GetMapping("/saveStockList")
    public void saveStockList() throws IOException {
        stockExternalApiService.saveStockList();
    }

    @GetMapping("/getStockPriceList")
    public SymbolAndPriceListDTO getStockPriceList() {
        return stockExternalApiService.getStockPriceList();
    }

    @GetMapping("/updateStockPriceList")
    public void updateStockPriceList() {
        stockExternalApiService.updateStockPriceList();
    }
}

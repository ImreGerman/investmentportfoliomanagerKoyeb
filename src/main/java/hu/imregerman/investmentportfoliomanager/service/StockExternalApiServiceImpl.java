package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.dto.SymbolAndPriceDTO;
import hu.imregerman.investmentportfoliomanager.dto.SymbolAndPriceListDTO;
import hu.imregerman.investmentportfoliomanager.model.Stock;
import hu.imregerman.investmentportfoliomanager.repository.StockExternalApiRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class StockExternalApiServiceImpl implements StockExternalApiService {

    private RestTemplate restTemplate;
    private StockExternalApiRepository stockExternalApiRepository;

    public StockExternalApiServiceImpl(RestTemplate restTemplate, StockExternalApiRepository stockExternalApiRepository) {
        this.restTemplate = restTemplate;
        this.stockExternalApiRepository = stockExternalApiRepository;
    }

    @Value("${external.api.url}")
    private String stockApiBaseUrl;

    @Value("${external.api.key}")
    private String apiKey;

    @Override
    public Stock[] getStockList() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        ResponseEntity<Stock[]> listResponseEntity =
                restTemplate.exchange(stockApiBaseUrl + "stock/list?apikey=" + apiKey, HttpMethod.GET, httpEntity, Stock[].class);
        return listResponseEntity.getBody();
    }

    public void saveStockList() {
        Stock[] stockList = getStockList();
        for (Stock stockDb : stockList) {
            Stock stock = new Stock();
            stock.setSymbol(stockDb.getSymbol());
            stock.setName(stockDb.getName());
            stock.setPrice(stockDb.getPrice());
            stock.setExchange(stockDb.getExchange());
            stock.setExchangeShortName(stockDb.getExchangeShortName());
            stock.setType(stockDb.getType());
            stock.setLastUpdate(ZonedDateTime.now());
            stockExternalApiRepository.save(stockDb);
        }
    }

    @Override
    public SymbolAndPriceListDTO getStockPriceList() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        ResponseEntity<SymbolAndPriceListDTO> responseEntity =
                restTemplate.exchange(stockApiBaseUrl + "stock/real-time-price?apikey=" + apiKey, HttpMethod.GET, httpEntity, SymbolAndPriceListDTO.class);
        return responseEntity.getBody();
    }

    @Override
    @Scheduled(cron = "@hourly") // TODO hourly every weekday
    public int updateStockPriceList() {
        int updateRows = 0;
        SymbolAndPriceListDTO priceList = getStockPriceList();
        for (SymbolAndPriceDTO symbolAndPriceDTO : priceList.getStockList()) {
            updateRows = stockExternalApiRepository
                    .updateStockPriceBySymbol(symbolAndPriceDTO.getPrice(), ZonedDateTime.now(), symbolAndPriceDTO.getSymbol());
        }
        return updateRows;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }
}



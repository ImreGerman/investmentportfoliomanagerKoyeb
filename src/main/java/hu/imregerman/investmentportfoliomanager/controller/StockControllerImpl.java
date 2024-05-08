package hu.imregerman.investmentportfoliomanager.controller;

import hu.imregerman.investmentportfoliomanager.dto.UserStockDTO;
import hu.imregerman.investmentportfoliomanager.model.Dividend;
import hu.imregerman.investmentportfoliomanager.model.Stock;
import hu.imregerman.investmentportfoliomanager.model.Transaction;
import hu.imregerman.investmentportfoliomanager.service.DividendService;
import hu.imregerman.investmentportfoliomanager.service.StockExternalApiService;
import hu.imregerman.investmentportfoliomanager.service.StockService;
import hu.imregerman.investmentportfoliomanager.service.UserService;
import hu.imregerman.investmentportfoliomanager.validation.TransactionValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
public class StockControllerImpl implements StockController {

    StockService stockService;
    UserService userService;
    DividendService dividendService;
    TransactionValidator transactionValidator;
    StockExternalApiService stockExternalApiService;

    public StockControllerImpl(StockService stockService, UserService userService, DividendService dividendService,
                               TransactionValidator transactionValidator, StockExternalApiService stockExternalApiService) {
        this.stockService = stockService;
        this.userService = userService;
        this.dividendService = dividendService;
        this.transactionValidator = transactionValidator;
        this.stockExternalApiService = stockExternalApiService;
    }

    @Override
    public String stockList(Model model, @AuthenticationPrincipal User user) {
        List<UserStockDTO> userAllStocks = stockService.getUserAllStocks(user);
        String generatedRandomUUID = String.valueOf(UUID.randomUUID());
        stockService.closeStockAndRelatedDividends(userAllStocks, generatedRandomUUID, user);

        model.addAttribute("apiKey", stockExternalApiService.getApiKey());
        model.addAttribute("stockList", stockService.getUserAllStocks(user));
        log.info(userAllStocks.toString());
        return "stock_list";
    }

    @Override
    public String stockTransactions(Model model, @RequestParam("stockId") UUID stockId, @AuthenticationPrincipal User user) {
        List<Transaction> transactions = stockService.getStockAllTransactions(stockId, user);
        model.addAttribute("transactions", transactions);
        return "stock_transaction_list";
    }

    @Override
    public String createNewTransaction(Model model, @RequestParam("stockId") UUID stockId, @AuthenticationPrincipal User user) {
        Transaction transaction = new Transaction();
        Stock stock = new Stock();
        stock.setId(stockId);
        transaction.setStock(stock);
        transaction.setUser(userService.getUserByName(user.getUsername()));
        model.addAttribute("transaction", transaction);
        return "transaction_form";
    }

    @Override
    public String addNewTransaction(@Valid Transaction transaction, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        transactionValidator.validateTransactionAmountSign(transaction, bindingResult);
        if (bindingResult.hasErrors()) {
            return "transaction_form";
        }
        redirectAttributes.addAttribute("stockId", transaction.getStock().getId());
        stockService.saveTransaction(transaction);
        return "redirect:/stock-transactions";
    }

    @Override
    public String modifyTransaction(@RequestParam("transactionId") UUID transactionId, Model model) {
        model.addAttribute("transaction", stockService.getTransactionById(String.valueOf(transactionId)));
        return "transaction_form";
    }

    @Override
    public String deleteTransaction(@RequestParam("transactionId") String transactionId, RedirectAttributes redirectAttributes) {
        Transaction transaction = stockService.getTransactionById(transactionId);
        UUID stockId = transaction.getStock().getId();

        stockService.removeTransaction(transactionId);
        redirectAttributes.addAttribute("stockId", stockId);
        return "redirect:/stock-transactions";
    }

    @Override
    public String getAllStock(Model model, String keyword) {
        model.addAttribute("stock", stockService.getAllStock(keyword));
        log.info(stockService.getAllStock(keyword).stream().toList().toString());
        return "all_stock";
    }

    @Override
    public String createNewStock(Model model, @RequestParam("stockId") UUID stockId, @AuthenticationPrincipal User user) {
        Transaction transaction = new Transaction();
        Stock stock = new Stock();
        stock.setId(stockId);
        transaction.setStock(stock);
        transaction.setUser(userService.getUserByName(user.getUsername()));
        model.addAttribute("transaction", transaction);
        return "add_new_stock_form";
    }

    @Override
    public String addNewStock(@Valid Transaction transaction, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        transactionValidator.validateTransactionAmountSign(transaction, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add_new_stock_form";
        }
        redirectAttributes.addAttribute("stockId", transaction.getStock().getId());
        stockService.saveTransaction(transaction);
        return "redirect:/stock-transactions";
    }

    @Override
    public String stockDividends(Model model, @RequestParam("stockId") UUID stockId, @AuthenticationPrincipal User user) {
        Dividend newDividend = new Dividend();
        Stock stock = new Stock();
        stock.setId(stockId);
        newDividend.setStock(stock);
        newDividend.setUser(userService.getUserByName(user.getUsername()));
        List<Dividend> dividends = unwrapOptionalDividends(stockId, user);

        model.addAttribute("newDividend", newDividend);
        model.addAttribute("dividends", dividends);
        log.info(stockService.getStockAllDividends(stockId, user).toString());
        return "dividend_list";
    }

    @Override
    public String addNewDividend(@Valid @ModelAttribute("newDividend") Dividend dividend, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user, Model model) {
        if (bindingResult.hasErrors()) {
            List<Dividend> dividends = unwrapOptionalDividends(dividend.getStock().getId(), user);
            model.addAttribute("dividends", dividends);
            return "dividend_list";
        }
        dividendService.saveDividend(dividend);
        redirectAttributes.addAttribute("stockId", dividend.getStock().getId());
        return "redirect:/stock-dividends";
    }

    @Override
    public String modifyDividend(@RequestParam String dividendId, Model model, Dividend dividend, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        model.addAttribute("modifyDividend", dividendService.getDividendById(dividendId));
        return "modify_dividend";
    }

    @Override
    public String saveModifiedDividend(@Valid @ModelAttribute("modifyDividend") Dividend dividend, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "modify_dividend";
        }
        redirectAttributes.addAttribute("stockId", dividend.getStock().getId());
        dividendService.saveDividend(dividend);
        return "redirect:/stock-dividends";
    }

    @Override
    public String deleteDividend(@RequestParam String dividendId, RedirectAttributes redirectAttributes) {
        Dividend dividend = dividendService.getDividendById(dividendId);
        redirectAttributes.addAttribute("stockId", dividend.getStock().getId());
        dividendService.removeDividend(dividendId);
        return "redirect:/stock-dividends";
    }

    @Override
    public String closedStockList(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("closedStockList", stockService.getUserAllClosedStocks(user));
        log.info(stockService.getUserAllClosedStocks(user).toString());
        return "closed_stock_list";
    }

    private List<Dividend> unwrapOptionalDividends(UUID stockId, User user) {
        List<Dividend> dividends = new ArrayList<>();
        Optional<List<Dividend>> OptDividends = stockService.getStockAllDividends(stockId, user);
        if (OptDividends.isPresent()) {
            dividends = OptDividends.get();
        }
        return dividends;
    }
}

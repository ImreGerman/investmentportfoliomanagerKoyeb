package hu.imregerman.investmentportfoliomanager.controller;

import hu.imregerman.investmentportfoliomanager.model.Dividend;
import hu.imregerman.investmentportfoliomanager.model.Transaction;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

public interface StockController {

    @GetMapping("/stock-list")
    String stockList(Model model, User user);

    @RequestMapping("/stock-transactions")
    String stockTransactions(Model model, UUID stockId, User user);

    @PostMapping("/new-transaction")
    String createNewTransaction(Model model, UUID stockId, User user);

    @PostMapping("/add-new-transaction")
    String addNewTransaction(Transaction transaction, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @PostMapping("/modify-transaction")
    String modifyTransaction(UUID transactionId, Model model);

    @PostMapping("/delete-transaction")
    String deleteTransaction(String transactionId, RedirectAttributes redirectAttributes);

    @GetMapping("/stocks")
    String getAllStock(int pageNumber, Model model, String keyword);

    @PostMapping("/new-stock")
    String createNewStock(Model model, UUID stockId, User user);

    @PostMapping("/add-new-stock")
    String addNewStock(Transaction transaction, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @RequestMapping("/stock-dividends")
    String stockDividends(Model model, UUID stockId, User user);

    @PostMapping("/add-new-dividend")
    String addNewDividend(Dividend dividend, BindingResult bindingResult, RedirectAttributes redirectAttributes, User user, Model model);

    @PostMapping("/modify-dividend")
    String modifyDividend(String dividendId, Model model, Dividend dividend, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @PostMapping("/save-dividend")
    String saveModifiedDividend(Dividend dividend, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @PostMapping("/delete-dividend")
    String deleteDividend(String dividendId, RedirectAttributes redirectAttributes);

    @GetMapping("/closed-stock-list")
    String closedStockList(Model model, User user);
}

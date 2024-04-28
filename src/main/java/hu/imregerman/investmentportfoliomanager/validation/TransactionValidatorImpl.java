package hu.imregerman.investmentportfoliomanager.validation;

import hu.imregerman.investmentportfoliomanager.model.Transaction;
import hu.imregerman.investmentportfoliomanager.model.TransactionType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Component
public class TransactionValidatorImpl implements TransactionValidator {

    @Override
    public void validateTransactionAmountSign(Transaction transaction, BindingResult bindingResult) {
        String transactionType = transaction.getTransactionType().toString();

        if (transaction.getTransactionAmount() != null) {
            if ("BUY".equals(transactionType) && transaction.getTransactionAmount().compareTo((double) 0) <= 0) {
                bindingResult.rejectValue("transactionAmount", "positive.required",
                        "Transaction amount must be positive when transaction type is BUY");
            } else if ("SELL".equals(transactionType) && transaction.getTransactionAmount().compareTo((double) 0) >= 0) {
                bindingResult.rejectValue("transactionAmount", "negative.required",
                        "Transaction amount must be negative when transaction type is SELL");
            }
        }
    }

    @Override
    public boolean isSumStockQuantityNegative(Transaction transaction, BindingResult bindingResult) {
        Set<Transaction> transactions = transaction.getUser().getTransactions();
        double sumStockQuantity = 0;
        int transactionSize = 0;

        for (Transaction t : transactions) {
            if (t.getStock().getId().equals(transaction.getStock().getId())) {
                transactionSize++;
                sumStockQuantity += t.getStockQuantity();
            }
        }

        if (transactionSize == 1) {
            String transactionType = transaction.getTransactionType().toString();
            if ("SELL".equals(transactionType)) {
                bindingResult.rejectValue("transactionType", "",
                        "In the case of a single BUY transaction, cannot modify it to SELL: The transaction must be deleted.");
            }
        }

        double remainingStockQuantity = sumStockQuantity + transaction.getStockQuantity();
        if (remainingStockQuantity < 0) {
            bindingResult.rejectValue("stockQuantity", "positive.required",
                    "Quantity of shares to sell exceeds the existing quantity. Sellable quantity: " + sumStockQuantity);
        }
        return false;
    }

//    @Override
//    public boolean isSumStockQuantityNegative(Transaction transaction, BindingResult bindingResult) {
//        Set<Transaction> transactions = transaction.getUser().getTransactions();
//        double sumStockQuantity = transactions.stream()
//                .filter(t -> t.getStock().getId().equals(transaction.getStock().getId()))
//                .mapToDouble(Transaction::getStockQuantity)
//                .sum();
//
//        if (transactions.size() == 1 && transaction.getTransactionType() == TransactionType.SELL) {
//            bindingResult.rejectValue("transactionType", "",
//                    "In the case of a single BUY transaction, cannot modify it to SELL: The transaction must be canceled.");
//        }
//
//        double remainingStockQuantity = sumStockQuantity + transaction.getStockQuantity();
//        if (remainingStockQuantity < 0) {
//            bindingResult.rejectValue("stockQuantity", "positive.required",
//                    "Quantity of shares to sell exceeds the existing quantity. Sellable quantity: " + sumStockQuantity);
//        }
//        return false;
//    }

}

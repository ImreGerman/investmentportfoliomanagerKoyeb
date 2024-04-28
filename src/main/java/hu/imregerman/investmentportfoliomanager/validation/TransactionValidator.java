package hu.imregerman.investmentportfoliomanager.validation;

import hu.imregerman.investmentportfoliomanager.dto.UserStockDTO;
import hu.imregerman.investmentportfoliomanager.model.Transaction;
import org.springframework.validation.BindingResult;

public interface TransactionValidator {

    void validateTransactionAmountSign(Transaction transaction, BindingResult bindingResult);

    boolean isSumStockQuantityNegative(Transaction transaction, BindingResult bindingResult);
}

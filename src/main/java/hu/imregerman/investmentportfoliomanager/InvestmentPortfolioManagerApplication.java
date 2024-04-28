package hu.imregerman.investmentportfoliomanager;

import hu.imregerman.investmentportfoliomanager.service.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableScheduling
public class InvestmentPortfolioManagerApplication implements CommandLineRunner {

	private StockService transactionService;

	public InvestmentPortfolioManagerApplication(StockService transactionService) {
		this.transactionService = transactionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(InvestmentPortfolioManagerApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
	}
}

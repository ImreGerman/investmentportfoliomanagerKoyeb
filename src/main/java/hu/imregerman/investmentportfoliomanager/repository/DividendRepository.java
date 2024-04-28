package hu.imregerman.investmentportfoliomanager.repository;

import hu.imregerman.investmentportfoliomanager.model.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, UUID> {
}

package ru.domain.purchaser.repository;

import org.springframework.data.repository.CrudRepository;
import ru.domain.purchaser.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findByOutNumber(String outNumber);
    List<Purchase> findByInNumber(String inNumber);

}
package com.trendyol.order.reporting.app.repository;

import com.trendyol.order.reporting.app.model.TopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopProductRepository extends JpaRepository<TopProduct, Long> {

    Optional<TopProduct> findByProductCode(String productCode);

}

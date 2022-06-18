package com.trendyol.order.reporting.app.repository;

import com.trendyol.order.reporting.app.model.SellerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellerPerformanceRepository extends JpaRepository<SellerPerformance, Long> {

    Optional<SellerPerformance> findBySellerName(String sellerName);

}

package com.trendyol.order.reporting.app.repository;

import com.trendyol.order.reporting.app.model.TopSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopSellerRepository extends JpaRepository<TopSeller, Long> {

    Optional<TopSeller> findBySellerName(String sellerName);

}

package com.trendyol.order.reporting.app.repository;

import com.trendyol.order.reporting.app.model.TopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopCategoryRepository extends JpaRepository<TopCategory, Long> {

    Optional<TopCategory> findByCategoryName(String category);

}

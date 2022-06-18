package com.trendyol.order.reporting.app.repository;

import com.trendyol.order.reporting.app.model.TopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopUserRepository extends JpaRepository<TopUser, Long> {

    Optional<TopUser> findByUserName(String userName);


}

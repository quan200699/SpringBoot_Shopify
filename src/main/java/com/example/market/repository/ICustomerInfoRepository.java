package com.example.market.repository;

import com.example.market.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
}

package com.searchEngine.searchEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchEngine.searchEngine.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}

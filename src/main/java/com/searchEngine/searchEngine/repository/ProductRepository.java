package com.searchEngine.searchEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchEngine.searchEngine.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}

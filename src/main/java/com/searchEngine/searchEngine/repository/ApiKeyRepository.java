package com.searchEngine.searchEngine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchEngine.searchEngine.entity.ApiKey;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Integer> {
    Optional<ApiKey> findByKey(String key);
    
}

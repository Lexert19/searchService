package com.searchEngine.searchEngine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Setter;

@Entity
@Setter
public class ApiKey {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "api_key")
    private String key;

    public ApiKey(String key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

}

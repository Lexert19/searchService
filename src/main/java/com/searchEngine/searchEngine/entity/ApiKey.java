package com.searchEngine.searchEngine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.Setter;

@Entity
@Setter
@Data
public class ApiKey {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "api_key", unique = true)
    private String key;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ApiKey(String key, User user) {
        this.key = key;
        this.user = user;
    }

    public ApiKey() {
    }
    
    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

}

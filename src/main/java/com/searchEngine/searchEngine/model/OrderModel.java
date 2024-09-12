package com.searchEngine.searchEngine.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderModel {
    private List<Integer> productsId;

    public OrderModel(){
        productsId = new ArrayList<>();
    }

}

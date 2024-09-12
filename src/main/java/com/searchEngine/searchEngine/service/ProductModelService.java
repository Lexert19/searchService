package com.searchEngine.searchEngine.service;

import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.model.ProductModel;

@Service
public class ProductModelService {
    public Product createProduct(ProductModel productModel){
        Product product = new Product();

        product.setCost(productModel.getPrice());
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        

        return product;
    }
}

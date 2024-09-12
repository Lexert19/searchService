package com.searchEngine.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.model.ProductModel;
import com.searchEngine.searchEngine.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductModelService productModelService;
    @Autowired
    private ProductRepository productRepository;
    
    public Product addProduct(ProductModel productModel){
        Product product = productModelService.createProduct(productModel);

        product = productRepository.saveAndFlush(product);

        return product;
    }
}

package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.model.ProductModel;
import com.searchEngine.searchEngine.service.ProductService;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct(){
        ProductModel productModel = new ProductModel();
        productModel.setDescription("test");
        productModel.setName("test");
        productModel.setPrice(12.00);

        Product product = productService.addProduct(productModel);

        assertTrue(product.getName().equals(productModel.getName()));
        assertTrue(product.getCost() == productModel.getPrice());
        assertTrue(product.getDescription().equals(productModel.getDescription()));
    }
}

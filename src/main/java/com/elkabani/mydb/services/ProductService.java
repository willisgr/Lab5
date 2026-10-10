package com.elkabani.mydb.services;

import com.elkabani.mydb.entities.Category;
import com.elkabani.mydb.entities.Product;
import com.elkabani.mydb.repositories.CategoryRepository;
import com.elkabani.mydb.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // new product with new category
    @Transactional
    public void addProduct(Product product) {
        var category = product.getCategory();
        categoryRepository.save(category);
        productRepository.save(product);
    }

    // new product with existing category
    @Transactional
    public void addProduct(Product product, Long categoryId) {
        var category = categoryRepository.findById(categoryId).orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
    }

    // remove a product from database
    @Transactional
    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

package com.elkabani.mydb.controllers;

import com.elkabani.mydb.dtos.CreatedProductDto;
import com.elkabani.mydb.dtos.ProductDto;
import com.elkabani.mydb.entities.Product;
import com.elkabani.mydb.mappers.ProductMapper;
import com.elkabani.mydb.repositories.CategoryRepository;
import com.elkabani.mydb.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(@RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId)
                    .stream()
                    .map(product ->
                    productMapper.toDto(product)).toList();
        }
        return productRepository.findAll()
                .stream()
                .map(product ->
                productMapper.toDto(product)).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<CreatedProductDto> createProduct(ProductDto data) {
        var category = categoryRepository.findById(data.getCategoryId())
                .orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toEntity(data);
        product.setCategory(category);
        var savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productMapper.toCreatedDto(savedProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedProductDto> updateProduct(@PathVariable Long id, ProductDto data) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        var category = categoryRepository.findById(data.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        productMapper.update(data, product);
        var updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(productMapper.toCreatedDto(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}

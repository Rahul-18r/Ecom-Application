package com.app.ecom.service;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductResponse> getProduct(Long id) {
        return productRepository.findById(id).map(this::mapToProductResponse);
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapToProduct(productRequest);
        return mapToProductResponse(productRepository.save(product));
    }

    public boolean updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(productRequest.getName());
                    existing.setDescription(productRequest.getDescription());
                    existing.setPrice(productRequest.getPrice());
                    existing.setStockQuantity(productRequest.getStockQuantity());
                    existing.setCategory(productRequest.getCategory());
                    existing.setImageUrl(productRequest.getImageUrl());
                    if (productRequest.getActive() != null) {
                        existing.setActive(productRequest.getActive());
                    }
                    productRepository.save(existing);
                    return true;
                })
                .orElse(false);
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategory(product.getCategory());
        response.setImageUrl(product.getImageUrl());
        response.setActive(product.getActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }

    private Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        if (productRequest.getActive() != null) {
            product.setActive(productRequest.getActive());
        }
        return product;
    }

    public Optional<ProductResponse> deleteById(Long id) {
        Optional<ProductResponse> productResponse=productRepository.findById(id).map(this::mapToProductResponse);
        if (productResponse.isPresent()) {
            productRepository.deleteById(id);
        }
        return productResponse;
    }
}


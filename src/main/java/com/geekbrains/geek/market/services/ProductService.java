package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> getMaxPrice(int page, int size,Float max) {
        return productRepository.getProductByPriceGreaterThanEqual(max, PageRequest.of(page,size));
    }

    public Page<Product> getMinPrice(int page, int size,Float min) {
        return productRepository.getProductByPriceLessThanEqual(min, PageRequest.of(page,size));
    }

    public Page<Product> getMinAndMaxPrice(int page, int size,  Float max, Float min) {
        return productRepository.getProductByPriceGreaterThanEqualAndPriceLessThanEqual(min, max,PageRequest.of(page,size));
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}

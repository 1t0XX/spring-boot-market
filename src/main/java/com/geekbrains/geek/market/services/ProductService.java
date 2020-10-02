package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.repositories.ProductRepository;
import com.geekbrains.geek.market.repositories.specifications.ProductSpecifications;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public void saveById(Long id, String title, int price){
        Product product = findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + "doesn't exists"));
        product.setPrice(price);
        product.setTitle(title);
        productRepository.save(product);
    }
}

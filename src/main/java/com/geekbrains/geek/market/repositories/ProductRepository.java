package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> getProductByPriceGreaterThanEqual(Float min, Pageable var);

    Page<Product> getProductByPriceLessThanEqual(Float max, Pageable var);

    Page<Product> getProductByPriceGreaterThanEqualAndPriceLessThanEqual(Float min, Float max, Pageable var);
}

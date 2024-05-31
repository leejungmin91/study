package com.min.store.product.repository;

import com.min.store.product.domain.Product;
import com.min.store.product.domain.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
}

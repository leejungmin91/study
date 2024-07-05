package com.min.store.product.repository;

import com.min.store.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
    boolean existsByName(String name);
}

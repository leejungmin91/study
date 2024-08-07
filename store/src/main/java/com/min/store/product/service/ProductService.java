package com.min.store.product.service;


import com.min.store.product.domain.*;
import com.min.store.product.entity.ProductEntity;
import com.min.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDomain> getProducts(){
        List<ProductEntity> productEntities = productRepository.findAll();

        return productEntities.stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    public ProductDomain getById(Long id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return product.toDomain();
    }

    public ProductDomainKotlin getByIdKotlin(Long id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        //return product.toDomain();
        return product.toDomainKotlin();
    }

    @Transactional
    public ProductDomain register(ProductCreateDomainKotlin productCreateDomain){

        ProductDomain productDomain = ProductDomain.from(productCreateDomain.getName(), productCreateDomain.getPrice());

        duplicateProductCheck(productDomain);

        ProductEntity productEntity = ProductEntity.from(productDomain);

        return productRepository.save(productEntity)
                .toDomain();
    }

    private void duplicateProductCheck(ProductDomain productDomain){
        boolean isProduct = productRepository.existsByName(productDomain.getName());
        if(isProduct) throw new IllegalStateException("이미 등록된 상품입니다.");
    }

    @Transactional
    public ProductDomain update(Long id, ProductUpdateDomain productUpdateDomain){
        ProductDomain productDomain = getById(id);
        productDomain.update(productUpdateDomain);

        return productRepository.save(ProductEntity.from(productDomain))
                .toDomain();
    }
}

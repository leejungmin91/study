package com.min.store.product.service;


import com.min.store.common.http.ApiResponse;
import com.min.store.product.domain.Product;
import com.min.store.product.dto.request.ProductFormRequestDto;
import com.min.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository memberRepository;

    public ApiResponse getProduct(Long id){
        Product product = memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return ApiResponse.success(product.toProductResponseDto());
    }

    @Transactional
    public ApiResponse register(ProductFormRequestDto productFormRequestDto){
        Product member = Product.builder()
                .name(productFormRequestDto.getName())
                .build();

        memberRepository.save(member);

        return ApiResponse.success();
    }
}

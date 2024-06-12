package com.min.store.product.service;


import com.min.store.common.http.ApiResponse;
import com.min.store.common.util.EntityConverter;
import com.min.store.product.domain.Product;
import com.min.store.product.domain.ProductId;
import com.min.store.product.dto.request.ProductFormRequestDto;
import com.min.store.product.dto.response.ProductResponseDto;
import com.min.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ApiResponse getProducts(){
        List<Product> product = productRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(p -> p.getProductId().getId()))
                .toList();

        List<ProductResponseDto> responseDtos = product.stream()
                .map(p -> EntityConverter.toResponse(p, ProductResponseDto.class))
                .toList();

        return ApiResponse.success(responseDtos);
    }

    public ApiResponse getProduct(Long id){
        Product product = productRepository.findById(new ProductId(id))
                .orElseThrow(EntityNotFoundException::new);

        return ApiResponse.success(EntityConverter.toResponse(product, ProductResponseDto.class));
    }

    @Transactional
    public ApiResponse register(ProductFormRequestDto productFormRequestDto){
        Product product = EntityConverter.toEntity(productFormRequestDto, Product.class);//Product.toEntity(productFormRequestDto);

        duplicateProductCheck(product);

        productRepository.save(product);

        return ApiResponse.success();
    }

    private void duplicateProductCheck(Product product){
        boolean isProduct = productRepository.existsByName(product.getName());
        if(isProduct) throw new IllegalStateException("이미 등록된 상품입니다.");
    }

    @Transactional
    public ApiResponse update(ProductFormRequestDto productFormRequestDto){
        Product product = productRepository.findById(new ProductId(productFormRequestDto.getId()))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));
        product.updateProduct(productFormRequestDto.getName(), productFormRequestDto.getPrice());

        return ApiResponse.success(product);
    }
}

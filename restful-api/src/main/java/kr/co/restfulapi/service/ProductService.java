package kr.co.restfulapi.service;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.ProductCreateDto;
import kr.co.restfulapi.dto.ProductDto;
import kr.co.restfulapi.dto.SignUpRequest;
import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.entity.Product;
import kr.co.restfulapi.repository.MemberRepository;
import kr.co.restfulapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 전체 상품 조회
     */
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDto::toDto)
                .toList();
    }

    /**
     * 상품 정보 검색
     */
    public ProductDto findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품번호를 확인해주세요."));
        return ProductDto.toDto(product);
    }

    /**
     * 상품 정보 수정
     */
    public void updateProduct(ProductDto productDto) {
        productRepository.findById(productDto.getProductId())
                .ifPresent(p -> p.updateProduct(productDto.getProductName(), productDto.getPrice(), productDto.getDiscountRate()));
    }

    /**
     * 상품 등록
     */
    public ProductDto createProduct(ProductCreateDto request) {
        Product requestEntity = request.toEntity();
        Product product = productRepository.save(requestEntity);
        return ProductDto.toDto(product);
    }

}

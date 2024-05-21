package kr.co.restfulapi.rest;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.ProductCreateDto;
import kr.co.restfulapi.dto.ProductDto;
import kr.co.restfulapi.dto.SignUpRequest;
import kr.co.restfulapi.service.MemberService;
import kr.co.restfulapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> findProduct(@PathVariable Long productId) {
		ProductDto productDto = productService.findById(productId);
		return ResponseEntity.ok(productDto);
	}

    @PostMapping
	public ResponseEntity<ProductDto> saveProduct(@Valid ProductCreateDto request) {
		ProductDto productDto = productService.createProduct(request);
		return ResponseEntity.ok(productDto);
	}

}

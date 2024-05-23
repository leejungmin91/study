package kr.co.restfulapi.service;

import kr.co.restfulapi.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Slf4j
class OrderServiceTest {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @Autowired
    public OrderServiceTest(OrderService orderService, MemberService memberService, ProductService productService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.productService = productService;
    }

    @BeforeEach
    void beforeEach() {
        SignUpRequestDto signUpRequest = SignUpRequestDto.builder()
                .email("min@test.com")
                .name("이정민")
                .password("1234")
                .build();

        memberService.join(signUpRequest);

        ProductRequestDto productRequestDto = ProductRequestDto.builder()
                .productName("Product")
                .discountRate(10L)
                .price(1000L)
                .build();

        productService.createProduct(productRequestDto);

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .mbrNo(0L)
                .productIds(List.of(0L))
                .build();

        orderService.order(orderRequestDto);
    }

    @Test
    void 회원_조회_테스트() {
        Long findOrderId = 0L;
        OrderDto orderDto = orderService.findOrder(findOrderId);
        assertThat(orderDto.getOrderId()).isEqualTo(findOrderId);
    }
}

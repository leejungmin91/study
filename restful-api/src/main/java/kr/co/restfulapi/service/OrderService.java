package kr.co.restfulapi.service;

import kr.co.restfulapi.dto.OrderDto;
import kr.co.restfulapi.dto.OrderRequestDto;
import kr.co.restfulapi.dto.ProductDto;
import kr.co.restfulapi.dto.ProductRequestDto;
import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.entity.Order;
import kr.co.restfulapi.entity.Product;
import kr.co.restfulapi.repository.MemberRepository;
import kr.co.restfulapi.repository.OrderRepository;
import kr.co.restfulapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    /**
     * 전체 주문내역 조회
     */
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::toDto)
                .toList();
    }

    /**
     * 특정 주문내역 조회
     */
    public OrderDto findOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        return OrderDto.toDto(order);
    }

    /**
     * 주문내역 저장
     */
    public OrderDto order(OrderRequestDto request) {
        List<Product> orderProducts = productRepository.findAllById(request.getProductIds());
        Member member = memberRepository.getById(request.getMbrNo());
        Order order = Order.builder()
                .member(member)
                .orderProducts(orderProducts)
                .build();

        Order saveOrder = orderRepository.save(order);

        return OrderDto.toDto(saveOrder);
    }
}

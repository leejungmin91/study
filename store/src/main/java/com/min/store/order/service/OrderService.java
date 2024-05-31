package com.min.store.order.service;


import com.min.store.common.exception.ApiException;
import com.min.store.common.http.ApiCode;
import com.min.store.common.http.ApiResponse;
import com.min.store.common.util.EntityConverter;
import com.min.store.member.domain.Member;
import com.min.store.member.domain.MemberId;
import com.min.store.order.domain.*;
import com.min.store.order.dto.request.OrderItemRequestDto;
import com.min.store.order.dto.request.OrderRequestDto;
import com.min.store.order.dto.response.OrderResponseDto;
import com.min.store.order.repository.OrderRepository;
import com.min.store.product.domain.Product;
import com.min.store.product.domain.ProductId;
import com.min.store.product.dto.request.ProductFormRequestDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public ApiResponse findOrder(Long id){
        Order order = orderRepository.findById(new OrderId(id))
                .orElseThrow(EntityNotFoundException::new);

        return ApiResponse.success(EntityConverter.toResponse(order, OrderResponseDto.class));
    }

    @Transactional
    public ApiResponse order(OrderRequestDto orderRequestDto){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        Long dateTimeAsLong = Long.parseLong(now.format(formatter));

        List<OrderItem> orderItems = orderRequestDto.getOrderItems()
                .stream()
                .map(dto -> {
                    ProductId productId = new ProductId(dto.getProductId());
                    return OrderItem.builder()
                            .product(productId)
                            .quantity(dto.getQuantity())
                            .orderPrice(dto.getPrice())
                            .build();
                }).toList();

        Member member = Member.currentMember()
                .orElseThrow(() -> new ApiException(ApiCode.ACCESS_DENIED));

        MemberId memberId = new MemberId(member.getId());

        Order order = Order.builder()
                .orderId(new OrderId(dateTimeAsLong))
                .orderer(new Orderer(memberId, member.getName()))
                .orderItems(orderItems)
                .status(OrderStatus.ORDER)
                .build();

        orderRepository.save(order);

        return ApiResponse.success();
    }
}

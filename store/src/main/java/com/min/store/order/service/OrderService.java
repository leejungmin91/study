package com.min.store.order.service;


import com.min.store.common.http.ApiResponse;
import com.min.store.common.util.EntityConverter;
import com.min.store.common.util.Utils;
import com.min.store.member.entity.MemberEntity;
import com.min.store.order.domain.*;
import com.min.store.order.dto.request.OrderRequestDto;
import com.min.store.order.dto.response.OrderResponseDto;
import com.min.store.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public List<Order> findByOrderer(Long id){
        return orderRepository.findByOrdererMemberId(new MemberId(id));
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

        MemberEntity member = Utils.currentMember();

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

package com.min.store.order.service;


import com.min.store.order.domain.OrderCreateDomain;
import com.min.store.order.domain.OrderDomain;
import com.min.store.order.entity.OrderEntity;
import com.min.store.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDomain findOrder(Long id){
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return order.toDomain();
    }

    /*public List<OrderEntity> findByOrderer(Long id){
        return orderRepository.findByOrdererMemberId(new MemberId(id));
    }*/

    @Transactional
    public OrderDomain order(OrderCreateDomain orderCreateDomain){

        OrderDomain orderDomain = OrderDomain.from(orderCreateDomain.getMember(), orderCreateDomain.getOrderItems(), orderCreateDomain.getStatus());

        OrderEntity order = OrderEntity.from(orderDomain);

        return orderRepository.save(order)
                .toDomain();
    }
}

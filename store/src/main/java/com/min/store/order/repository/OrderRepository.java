package com.min.store.order.repository;

import com.min.store.order.domain.Order;
import com.min.store.order.domain.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}

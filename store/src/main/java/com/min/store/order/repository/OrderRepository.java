package com.min.store.order.repository;

import com.min.store.member.domain.MemberId;
import com.min.store.order.domain.Order;
import com.min.store.order.domain.OrderId;
import com.min.store.order.domain.Orderer;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
    List<Order> findByOrdererMemberId(MemberId memberId);
}

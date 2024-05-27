package kr.co.restfulapi.repository;

import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}

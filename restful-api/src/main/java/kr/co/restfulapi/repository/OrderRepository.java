package kr.co.restfulapi.repository;

import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{

    /**
     * 중복 회원 확인 (EMAIL)
     */
    boolean existsByEmail(String email);

}

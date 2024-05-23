package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Order;
import kr.co.restfulapi.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class OrderDto {

    private Long orderId;
    private MemberDto member;
    private List<ProductDto> orderProducts;

    public static OrderDto toDto(Order order){
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderProducts(order.getOrderProducts().stream().map(ProductDto::toDto).toList())
                .member(MemberDto.toDto(order.getMember()))
                .build();
    }

}

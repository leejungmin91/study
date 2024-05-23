package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Order;
import kr.co.restfulapi.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequestDto {

    private Long mbrNo;
    private List<Long> productIds;

}

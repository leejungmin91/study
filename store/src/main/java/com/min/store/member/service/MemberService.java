package com.min.store.member.service;


import com.min.store.common.exception.ApiException;
import com.min.store.common.http.ApiCode;
import com.min.store.common.http.ApiResponse;
import com.min.store.common.util.EntityConverter;
import com.min.store.common.util.Utils;
import com.min.store.member.domain.Member;
import com.min.store.member.domain.MemberId;
import com.min.store.member.dto.request.SignUpRequestDto;
import com.min.store.member.repository.MemberRepository;
import com.min.store.order.domain.Order;
import com.min.store.order.domain.OrderItem;
import com.min.store.order.dto.response.OrderResponseDto;
import com.min.store.order.service.OrderService;
import com.min.store.product.domain.Product;
import com.min.store.product.domain.ProductId;
import com.min.store.product.dto.response.ProductResponseDto;
import com.min.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    public ApiResponse getMember(Long id){
        Member member = memberRepository.findById(new MemberId(id))
                .orElseThrow(EntityNotFoundException::new);

        return ApiResponse.success(member.toMemberResponseDto());
    }

    @Transactional
    public ApiResponse register(SignUpRequestDto signUpRequestDto){
        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .build();

        duplicateMemberCheck(member);

        memberRepository.save(member);

        return ApiResponse.success(member);
    }

    public ApiResponse getMemberOrders(){
        Member member = Utils.currentMember();
        List<Order> orders = orderService.findByOrderer(member.getId());

        List<OrderResponseDto> orderResponseDtos = orders.stream()
                .map(order -> {
                    Map<ProductId, OrderItem> productMap = order.getOrderItems()
                            .stream()
                            .collect(Collectors.toMap(OrderItem::getProduct, Function.identity()));

                    List<ProductId> productIds = order.getOrderItems()
                            .stream()
                            .map(OrderItem::getProduct)
                            .toList();

                    List<Product> products = productRepository.findAllById(productIds);

                    List<ProductResponseDto> productResponseDtos = products.stream()
                            .map(product -> {
                                ProductResponseDto res = EntityConverter.toResponse(product, ProductResponseDto.class);
                                Long orderPrice = productMap.get(product.getProductId()).getOrderPrice();
                                int quantity = productMap.get(product.getProductId()).getQuantity();
                                res.setOrderPrice(orderPrice);
                                res.setQuantity(quantity);
                                return res;
                            })
                            .toList();

                    OrderResponseDto orderResponseDto = EntityConverter.toResponse(order, OrderResponseDto.class);
                    orderResponseDto.setOrderItems(productResponseDtos);

                    return orderResponseDto;
                })
                .toList();

        return ApiResponse.success(orderResponseDtos);
    }

    private void duplicateMemberCheck(Member member){
        boolean isMember = memberRepository.existsByEmail(member.getEmail());
        if(!isMember) throw new ApiException(ApiCode.DUPLICATE_MEMBER);
    }
}

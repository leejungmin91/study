package com.min.store.member.service;


import com.min.store.common.exception.ApiException;
import com.min.store.common.http.ApiCode;
import com.min.store.common.http.ApiResponse;
import com.min.store.common.util.EntityConverter;
import com.min.store.common.util.Utils;
import com.min.store.member.domain.MemberDomain;
import com.min.store.member.domain.MemberSignUpDomain;
import com.min.store.member.entity.MemberEntity;
import com.min.store.member.dto.request.SignUpRequestDto;
import com.min.store.member.repository.MemberRepository;
import com.min.store.order.domain.Order;
import com.min.store.order.domain.OrderItem;
import com.min.store.order.dto.response.OrderResponseDto;
import com.min.store.order.service.OrderService;
import com.min.store.product.entity.ProductEntity;
import com.min.store.product.dto.ProductResponseDto;
import com.min.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    public MemberDomain getById(Long id){
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return member.toDomain();
    }

    @Transactional
    public MemberDomain register(MemberSignUpDomain memberSignUpDomain){
        MemberDomain memberDomain = MemberDomain.from(memberSignUpDomain.getEmail(), memberSignUpDomain.getName(), memberSignUpDomain.getPassword());

        duplicateMemberCheck(memberDomain);

        MemberEntity memberEntity = MemberEntity.from(memberDomain);

        return memberRepository.save(memberEntity)
                .toDomain();
    }

    public MemberDomain getMemberOrders(Long id){
        MemberEntity member = memberRepository.getOrdersById(id);

        return null;
    }

    private void duplicateMemberCheck(MemberDomain memberDomain){
        boolean isMember = memberRepository.existsByEmail(memberDomain.getEmail());
        if(!isMember) throw new ApiException(ApiCode.DUPLICATE_MEMBER);
    }
}

package com.store.memberapi.repository;

import com.store.memberapi.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryCustom {

    Optional<MemberEntity> findOrderById(Long id);
    
}

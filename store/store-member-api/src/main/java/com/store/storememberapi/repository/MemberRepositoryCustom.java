package java.com.store.storememberapi.repository;

import com.min.store.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryCustom {

    Optional<MemberEntity> findOrderById(Long id);
    
}

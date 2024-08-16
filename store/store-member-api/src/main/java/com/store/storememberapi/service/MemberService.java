package java.com.store.storememberapi.service;


import com.min.store.common.exception.ApiException;
import com.min.store.common.http.ApiCode;
import com.min.store.member.domain.MemberDomain;
import com.min.store.member.domain.MemberSignUpDomain;
import com.min.store.member.entity.MemberEntity;
import com.min.store.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDomain getById(Long id){
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return member.toDomain();
    }

    @Transactional
    public MemberDomain register(MemberSignUpDomain memberSignUpDomain){
        MemberDomain memberDomain = MemberDomain.from(memberSignUpDomain.getEmail(), memberSignUpDomain.getName(), passwordEncoder.encode(memberSignUpDomain.getPassword()));

        duplicateMemberCheck(memberDomain);

        MemberEntity memberEntity = MemberEntity.from(memberDomain);

        return memberRepository.save(memberEntity)
                .toDomain();
    }

    public MemberDomain getMemberOrders(Long id){
        MemberEntity member = memberRepository.findOrderById(id)
                .orElseThrow(EntityNotFoundException::new);
        return member.toDomain();
    }

    private void duplicateMemberCheck(MemberDomain memberDomain){
        boolean isMember = memberRepository.existsByEmail(memberDomain.getEmail());
        if(isMember) throw new ApiException(ApiCode.DUPLICATE_MEMBER);
    }
}

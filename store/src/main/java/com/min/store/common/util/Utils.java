package com.min.store.common.util;

import com.min.store.common.exception.ApiException;
import com.min.store.common.http.ApiCode;
import com.min.store.member.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

public class Utils {

    public static Member currentMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object details = authentication.getDetails();
            if(details instanceof Map map){
                return Member.builder()
                        .id(Long.parseLong(String.valueOf(map.get("id"))))
                        .email((String) map.get("email"))
                        .name((String) map.get("name"))
                        .build();
            }
        }

        throw new ApiException(ApiCode.ACCESS_DENIED);
    }

}

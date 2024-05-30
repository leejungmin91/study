package com.min.store.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T, U> T toResponse(U entity, Class<T> responseClass){
        return objectMapper.convertValue(entity, responseClass);
    }

    public static <T, U> T toEntity(U requestDto, Class<T> entityClass){
        return objectMapper.convertValue(requestDto, entityClass);
    }

}

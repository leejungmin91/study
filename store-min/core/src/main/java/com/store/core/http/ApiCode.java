package com.store.core.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiCode {

	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, String.valueOf(HttpStatus.UNAUTHORIZED.value())),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST.value())),
	NOT_FOUND(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value())),

	SUCCESS(HttpStatus.OK, "0000", "정상적으로 처리되었습니다."),
	FAIL(HttpStatus.OK,"1000", "요청이 실패했습니다."),
	DUPLICATE_MEMBER(HttpStatus.BAD_REQUEST, "1001", "이미 존재하는 회원입니다."),
	LOGIN_DENIED(HttpStatus.BAD_REQUEST, "1002", "아이디 또는 비밀번호를 확인해주세요."),
	ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "1003", "권한이 없습니다."),
	TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "1004", "토큰이 유효하지 않습니다."),
	TOKEN_EXPIRED(HttpStatus.INTERNAL_SERVER_ERROR, "1005", "토큰이 만료되었습니다."),

	;

	private HttpStatus status;
	private String code;
	private String message;

	ApiCode(HttpStatus status, String code){
		this.status = status;
		this.code = code;
	}

	ApiCode(HttpStatus status, String code, String message){
		this.status = status;
		this.code = code;
		this.message = message;
	}

}

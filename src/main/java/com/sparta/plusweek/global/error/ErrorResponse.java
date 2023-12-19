package com.sparta.plusweek.global.error;

import lombok.Getter;

// 에러 발생시 응답되는 DTO
// HttpStatus는 HTTP 응답 정보로 알 수 있으므로, message 만 포함
@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
}

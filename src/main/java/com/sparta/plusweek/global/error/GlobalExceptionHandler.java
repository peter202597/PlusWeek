package com.sparta.plusweek.global.error;

import com.sparta.plusweek.global.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;

import static com.sparta.plusweek.global.error.ErrorCode.*;


// 에러 핸들링 컨트롤러. 모든 에러는 여기서 처리.
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
        final ErrorResponse response = new ErrorResponse(POST_NOT_FOUND);
        return new ResponseEntity<>(response, POST_NOT_FOUND.getHttpStatus());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        final ErrorResponse response = new ErrorResponse(INVALID_PASSWORD);
        return new ResponseEntity<>(response, INVALID_PASSWORD.getHttpStatus());
    }

    // 잘못된 username, password 로 사용자를 찾을 수 없는 경우.
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(USER_NOT_FOUND);
        return new ResponseEntity<>(response, USER_NOT_FOUND.getHttpStatus());
    }

    // 회원가입 시 중복된 username 이 있는 경우.
    @ExceptionHandler(DuplicateUsernameException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateUsernameException(DuplicateUsernameException e) {
        ErrorResponse response = new ErrorResponse(DUPLICATE_USERNAME);
        return new ResponseEntity<>(response, DUPLICATE_USERNAME.getHttpStatus());
    }

    // 다른 사용자의 게시글이나 댓글에 접근하는 경우.
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        ErrorResponse response = new ErrorResponse(ACCESS_DENIED);
        return new ResponseEntity<>(response, ACCESS_DENIED.getHttpStatus());
    }



    // 커스텀 예외가 아닌, Validation 라이브러리 종속 예외.
    // 생성 및 수정 요청 시 검증되지 않은 입력값이 들어왔을 때 발생함.
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException e) {
        final ErrorResponse response = new ErrorResponse(SERVER_ERROR);
        return new ResponseEntity<>(response, SERVER_ERROR.getHttpStatus());
    }
}

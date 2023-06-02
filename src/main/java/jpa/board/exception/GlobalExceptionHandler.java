package jpa.board.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

// 컨트롤러 전역에서 발생할 수 있는 예외를 잡아 throw 해줌 (ContollerAdvice --> RestControllerAdvice)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handleCustomException(final CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity handleMethodNotSupportedException(final HttpRequestMethodNotSupportedException e){
        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity handlePageNotFoundException(final NoHandlerFoundException e){
        return ResponseEntity
                .status(ErrorCode.PAGE_NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.PAGE_NOT_FOUND));
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(final Exception e){
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}

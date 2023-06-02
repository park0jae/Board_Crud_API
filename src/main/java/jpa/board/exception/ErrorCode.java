package jpa.board.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     *  400 BAD_REQUEST : 잘못된 요청
     */
    BAD_REQUEST(400,HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    /**
     * 게시글 찾기 실패
     */
    POST_NOT_FOUND(404, HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."),

    /**
     * 요청 페이지 찾기 실패
     */
    PAGE_NOT_FOUND(404,HttpStatus.NOT_FOUND, "요청하신 페이지를 찾을 수 없습니다."),

    /**
     * 허용되지 않은 메서드 사용
     */
    METHOD_NOT_ALLOWED(405,HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    /**
     * 서버 오류
     */
    INTERNAL_SERVER_ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    ;

    private final int code;
    private final HttpStatus status;
    private final String message;

}

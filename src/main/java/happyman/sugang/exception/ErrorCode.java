package happyman.sugang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_ID(HttpStatus.CONFLICT, "아이디가 중복되었습니다"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "invalid parameter");

    private HttpStatus status;
    private String message;
}

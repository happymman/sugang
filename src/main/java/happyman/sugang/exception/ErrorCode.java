package happyman.sugang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_ID(HttpStatus.CONFLICT, "userId is duplicated."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "invalid parameter");

    private HttpStatus status;
    private String message;
}

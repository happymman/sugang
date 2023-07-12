package happyman.sugang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidParameterException extends RuntimeException{
    private ErrorCode errorCode;

    public InvalidParameterException(){
        this.errorCode = ErrorCode.INVALID_PARAMETER;
    }

    @Override
    public String toString(){
        return errorCode.getMessage();
    }
}

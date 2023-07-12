package happyman.sugang.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicatedIdException extends RuntimeException{
    private ErrorCode errorCode;

    public DuplicatedIdException(){
        this.errorCode = ErrorCode.DUPLICATED_USER_ID;
    }

    @Override
    public String toString(){
        return errorCode.getMessage();
    }

//    private String message;
//    //ex : SugangAppException(ErrorCode.DUPLICATED_ID, String.format("UserId :",userId);

//    @Override
//    public String toString(){
//        if(message == null){
//            return errorCode.getMessage();
//        }
//        return String.format("%s. %s", errorCode.getMessage(), message);
//    }
}

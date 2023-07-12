package happyman.sugang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {

    // 모든 RuntimeException 오류가 발생했을 때 동작하는 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseEntity<?> DuplicatedIdExceptionHandler(DuplicatedIdException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().name());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> InvalidParameterExceptionHandler(InvalidParameterException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().name());
    }
}
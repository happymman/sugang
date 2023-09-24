package happyman.sugang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseEntity<?> DuplicatedIdExceptionHandler(DuplicatedIdException e){

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processValidationError(MethodArgumentNotValidException e) {
        System.out.println("MethodArgumentNotValidException 진입");
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getField());
            builder.append("(은)는 ");
            builder.append(fieldError.getDefaultMessage());
        }

        return ResponseEntity
                .status(e.getStatusCode())
                .body(builder.toString());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> InvalidParameterExceptionHandler(InvalidParameterException e){
            System.out.println("InvalidParameterException 진입");
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().name());
    }

}
package com.twuc.webApp.exception;

import com.twuc.webApp.web.ExceptionController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @author shuang.kou
 */
@ControllerAdvice(assignableTypes = {ExceptionController.class})
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleAppException(BaseException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        ErrorReponse representation = new ErrorReponse(ex, path);
        return new ResponseEntity<>(representation, new HttpHeaders(), ex.getError().getStatus());
    }


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorReponse> exceptionHandler(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorReponse errorReponse = new ErrorReponse(ex, request.getRequestURI());
        System.out.println(errorReponse.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReponse);
    }
}

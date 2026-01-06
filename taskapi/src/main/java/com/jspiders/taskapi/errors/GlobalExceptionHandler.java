package com.jspiders.taskapi.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> arithmeticExceptionHandler(ArithmeticException ex)
    {
        System.out.println("Handling ArithmeticException");
        ex.printStackTrace();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex)
    {
        System.out.println("Handling NullPointerException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex)
    {
        System.out.println("Handling illegalArgumentException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name cannot be blank/empty");
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String> invalidNameExceptionHandler(InvalidNameException ex)
    {
        System.out.println("Handling illegalArgumentException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}

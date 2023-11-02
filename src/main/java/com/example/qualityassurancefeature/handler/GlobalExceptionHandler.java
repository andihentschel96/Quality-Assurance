package com.example.qualityassurancefeature.handler;

import com.example.qualityassurancefeature.exception.QueryOutOfBoundsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value =  {QueryOutOfBoundsException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseEntity<String> handleInvalidQueryParameter(QueryOutOfBoundsException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The request failed, error message: " + e.getLocalizedMessage());
    }
}

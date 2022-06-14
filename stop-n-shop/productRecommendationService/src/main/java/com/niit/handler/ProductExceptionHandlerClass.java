package com.niit.handler;


import com.ctc.wstx.shaded.msv_core.verifier.ErrorInfo;
import com.niit.domain.Product;
import com.niit.exception.CategoryNotFoundException;
import com.niit.exception.CityNotFoundException;
import com.niit.exception.EmptyInputFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




@ControllerAdvice
public class ProductExceptionHandlerClass {
    @ExceptionHandler({EmptyInputFieldException.class})
    private ResponseEntity<String> handleEmtyInputFieldException(EmptyInputFieldException exception){
        return new ResponseEntity("some fields are empty plz look for it",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CityNotFoundException.class})
    private ResponseEntity<String>handleCityNotFound(CityNotFoundException foundException){
        return new ResponseEntity("The Entered City is not present in the database",HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({CategoryNotFoundException.class})
    private ResponseEntity<String>handleCityNotFound(CategoryNotFoundException foundException){
        return new ResponseEntity("Category is not present in the database",HttpStatus.NOT_FOUND);

    }
}






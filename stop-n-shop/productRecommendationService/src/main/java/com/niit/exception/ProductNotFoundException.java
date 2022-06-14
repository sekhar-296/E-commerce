package com.niit.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NO_CONTENT , reason = "Product Not Found")
public class ProductNotFoundException extends Exception{
}
package com.stackroute.shopsListService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason="Shop already exists")
public class ShopAlreadyExistException extends Exception{
}

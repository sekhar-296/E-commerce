package com.stackroute.shopsListService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="No Shops Found")
public class NoShopFoundException extends Exception{
}

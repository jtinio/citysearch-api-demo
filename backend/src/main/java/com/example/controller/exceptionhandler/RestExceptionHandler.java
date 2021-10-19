package com.example.controller.exceptionhandler;

import com.example.commons.exceptions.InvalidRestRequestException;
import com.example.model.dto.resource.MessageResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({InvalidRestRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageResource handleInvalidRestRequestException(
            InvalidRestRequestException invalidRestRequestException) {
        return new MessageResource(invalidRestRequestException.getMessage());
    }

}
package com.github.darogina.ideas.controller;

import com.github.darogina.ideas.exception.NotFoundException;
import com.github.darogina.ideas.model.api.Error;
import com.github.darogina.ideas.model.api.ExtendedError;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            ServletRequestBindingException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Error handleRequestException(Exception ex) {
        return createGenericError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public
    @ResponseBody
    Error handleNotFoundException(NotFoundException ex) {
        return createGenericError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public
    @ResponseBody
    ExtendedError handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) throws IOException {
        Map<String, Object> info = new HashMap<>();
        info.put("supported", ex.getSupportedMediaTypes());

        return createExtendedError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex, info);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Error handleUncaughtException(Exception ex) throws IOException {
        return createGenericError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private Error createGenericError(HttpStatus status, Exception ex) {
        final Error error = new Error();
        error.setError(status);
        error.setMessage(ex.getLocalizedMessage());

        return error;
    }

    private ExtendedError createExtendedError(HttpStatus status, Exception ex, Map<String, Object> info) {
        final ExtendedError error = new ExtendedError();
        error.setError(status);
        error.setMessage(ex.getLocalizedMessage());
        error.setInfo(info);

        return error;
    }
}
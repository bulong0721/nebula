package com.rhea.kernel.advice;

import com.rhea.kernel.exchange.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;

/**
 * @author xubulong
 * @version V1.0 created at: 2018/10/15
 */
@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity errorHandler(AccessDeniedException ade) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity errorHandler(ConstraintViolationException cve) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandler(ValidationException ve) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity errorHandler(MissingServletRequestParameterException re) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity errorHandler(ServletRequestBindingException rbe) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity errorHandler(RuntimeException re) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity errorHandler(Exception re) {
        return null;
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity errorHandler(Throwable re) {
        return null;
    }
}

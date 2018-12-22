package com.rhea.web.advice;

import com.rhea.kernel.exception.FluentException;
import com.rhea.kernel.exception.ServiceException;
import com.rhea.kernel.exchange.ResponseEntity;
import com.rhea.kernel.exchange.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;

/**
 * @author xubulong
 * @version V1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity errorHandler(AccessDeniedException ade) {
        return StatusCode.Unauthorized.build(ade.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity errorHandler(ConstraintViolationException cve) {
        return StatusCode.ConstraintValidation.build(cve.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandler(ValidationException ve) {
        return StatusCode.ConstraintValidation.build(ve.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity errorHandler(MissingServletRequestParameterException re) {
        return StatusCode.BadRequest.build(re.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity errorHandler(ServletRequestBindingException rbe) {
        return StatusCode.BadRequest.build(rbe.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(FluentException.class)
    public ResponseEntity errorHandler(FluentException be) {
        return StatusCode.ConstraintValidation.build(be.getErrors());
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity errorHandler(ServiceException be) {
        return StatusCode.ServiceException.build(be.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity errorHandler(RuntimeException re) {
        return StatusCode.ServerError.build(re.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity errorHandler(Exception re) {
        return StatusCode.ServerError.build(re.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity errorHandler(Throwable re) {
        return StatusCode.ServerError.build(re.getMessage());
    }


}

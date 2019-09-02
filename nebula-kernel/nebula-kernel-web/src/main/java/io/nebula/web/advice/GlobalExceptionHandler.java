package io.nebula.web.advice;

import io.nebula.kernel.exception.BusinessException;
import io.nebula.kernel.exception.FluentException;
import io.nebula.kernel.exchange.IStatusCode;
import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
 */
@Slf4j
@Order(-1)
@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity logError(IStatusCode code, Throwable ex) {
        log.error(code.toString(), ex);
        return code.build(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity errorHandler(ConstraintViolationException ex, HttpServletRequest request) {
        return logError(StatusCode.ConstraintValidation, ex);
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandler(ValidationException ex, HttpServletRequest request) {
        return logError(StatusCode.ConstraintValidation, ex);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity errorHandler(MissingServletRequestParameterException ex, HttpServletRequest request) {
        return logError(StatusCode.MissingRequestParameter, ex);
    }

    @ResponseBody
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity errorHandler(ServletRequestBindingException ex, HttpServletRequest request) {
        return logError(StatusCode.RequestBinding, ex);
    }

    @ResponseBody
    @ExceptionHandler(FluentException.class)
    public ResponseEntity errorHandler(FluentException ex, HttpServletRequest request) {
        StatusCode code = StatusCode.ConstraintValidation;
        log.error(code.toString(), ex);
        return code.build(ex.getErrors());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity errorHandler(BusinessException ex, HttpServletRequest request) {
        return logError(StatusCode.ServiceException, ex);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity errorHandler(RuntimeException ex, HttpServletRequest request) {
        return logError(StatusCode.ServerError, ex);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity errorHandler(Exception ex, HttpServletRequest request) {
        return logError(StatusCode.ServerError, ex);
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity errorHandler(Throwable ex, HttpServletRequest request) {
        return logError(StatusCode.ServerError, ex);
    }
}

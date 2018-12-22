package com.rhea.kernel.exception;

import com.baidu.unbiz.fluentvalidator.ValidationError;

import java.util.List;

/**
 * @author xubulong
 * @version V1.0
 */
public class FluentException extends RuntimeException {
    private final List<ValidationError> errors;

    public FluentException(List<ValidationError> errors) {
        super("");
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}

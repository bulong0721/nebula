package io.nebula.kernel.exception;

import com.baidu.unbiz.fluentvalidator.ValidationError;

import java.util.List;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/14
 */
public class FluentException extends NebulaException {
    private final List<ValidationError> errors;

    public FluentException(List<ValidationError> errors) {
        super("");
        this.errors = errors;
    }

    /**
     * 验证错误
     *
     * @return
     */
    public List<ValidationError> getErrors() {
        return errors;
    }
}

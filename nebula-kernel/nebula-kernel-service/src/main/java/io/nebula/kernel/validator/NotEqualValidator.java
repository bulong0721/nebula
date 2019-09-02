package io.nebula.kernel.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/27
 */
public class NotEqualValidator extends ValidatorHandler implements Validator {
    private Object value;
    private String fieldName;

    public NotEqualValidator(Object value, String fieldName) {
        this.value = value;
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, Object integer) {
        if (value.equals(integer)) {
            context.addError(ValidationError.create(String.format("%s不能等于%s", fieldName, value))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(integer));
            return false;
        }
        return true;
    }

}

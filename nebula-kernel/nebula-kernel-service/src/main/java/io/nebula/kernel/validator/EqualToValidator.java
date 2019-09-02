package io.nebula.kernel.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class EqualToValidator extends ValidatorHandler implements Validator {
    private Object value;
    private String fieldName;

    public EqualToValidator(Object value, String fieldName) {
        this.value = value;
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, Object integer) {
        if (null == integer || !value.equals(integer)) {
            context.addError(ValidationError.create(String.format("%s不等于%s", fieldName, value))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(integer));
            return false;
        }
        return true;
    }

}

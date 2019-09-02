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
public class SizeValidator extends ValidatorHandler<Long> implements Validator<Long> {

    private long min;

    private long max;

    private String fieldName;

    public SizeValidator(long min, long max, String fieldName) {
        this.min = min;
        this.max = max;
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, Long integer) {
        if (null == integer || integer.intValue() > max || integer.intValue() < min) {
            context.addError(ValidationError.create(String.format("%s必须大于%s，小于%s", fieldName, min, max))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(integer));
            return false;
        }
        return true;
    }

}

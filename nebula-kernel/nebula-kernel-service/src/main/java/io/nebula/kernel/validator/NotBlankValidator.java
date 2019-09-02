package io.nebula.kernel.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import io.nebula.util.StringUtil;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class NotBlankValidator extends ValidatorHandler<String> implements Validator<String> {

    private String fieldName;

    public NotBlankValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (StringUtil.isBlank(s)) {
            context.addError(ValidationError.create(String.format("%s不能为空！", fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(s));
            return false;
        }
        return true;
    }

}

package com.rhea.upms.web.controller.upms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.rhea.common.base.BaseController;
import com.rhea.common.exception.RheaException;
import com.rhea.common.validator.NotNullValidator;
import com.rhea.upms.api.UpmsUserService;
import com.rhea.upms.model.UpmsUser;
import com.rhea.upms.model.UpmsUserExample;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * controller
 * Created by controller-generator on 2018-7-17
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UpmsUserController extends BaseController {
    @SofaReference(binding = @SofaReferenceBinding(bindingType = "dubbo"))
    private UpmsUserService userService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    private List<UpmsUser> search() {
        UpmsUserExample example = new UpmsUserExample();
        return userService.listByExample(example);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int add(@RequestBody UpmsUser user) {
        // 订单实体校验
        ComplexResult result = FluentValidator.checkAll().failOver()
            .on(user.getRealname(), new NotNullValidator("realname"))
            .doValidate()
            .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            throw new RheaException();
        }
        return userService.insertSelective(user);
    }
}

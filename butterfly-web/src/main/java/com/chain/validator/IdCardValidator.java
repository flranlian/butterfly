package com.chain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by lian.ran on 2018/4/3.
 */
public class IdCardValidator implements ConstraintValidator<IsIdCard, String> {
    /**
     * 校验前的初始化工作
     *
     * @param constraintAnnotation 自定义的校验注解
     */
    @Override
    public void initialize(IsIdCard constraintAnnotation) {
        String message = constraintAnnotation.message();
        System.out.println("用户自定义的message信息是：".concat(message));
    }
    /**
     * 具体的校验逻辑方法
     *
     * @param value   需要校验的值，从前端传递过来
     * @param context 校验器的校验环境
     * @return 通过校验返回true，否则返回false
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}

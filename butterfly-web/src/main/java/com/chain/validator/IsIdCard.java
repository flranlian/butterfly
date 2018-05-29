package com.chain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义身份证号码校验注解
 *
 * @author lian.ran
 * @date 2018/3/31 下午7:43
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardValidator.class) //指定了当前注解使用哪个类来进行校验
public @interface IsIdCard {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

package com.chain.com.chain.service.com.chain.service.impl;

/**
 * Created by lian.ran on 2018/4/3.
 */
public interface IdCardValidatorService {

    /**
     * 身份证号校验，支持18位、15位和港澳台的10位
     *
     * @param value 需要被校验的值
     * @return 校验通过返回true，否则返回false
     */
    boolean valid(String value);
}

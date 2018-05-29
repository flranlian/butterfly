package com.chain.com.chain.service.com.chain.service.impl;

import cn.hutool.core.util.IdcardUtil;
import org.springframework.stereotype.Service;

@Service
public class IdCardValidatorServiceImpl implements IdCardValidatorService {

    @Override
    public boolean valid(String value) {
        return IdcardUtil.isValidCard(value);
    }
}
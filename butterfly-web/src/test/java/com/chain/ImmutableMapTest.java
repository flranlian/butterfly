package com.chain;

import com.chain.newapi.ConstantMap;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by lian.ran on 2018/5/25.
 */
public class ImmutableMapTest {

    public static void main(String[] args) {
        immutableMapTest();
    }
    /**
     * 测试 guava ImmutableMap
     */
    private static void immutableMapTest() {
        Integer key = 30;
        System.out.println("key = " + key + "的提示语是：" + ConstantMap.INTEGER_STRING_MAP.get(key));
       // java.lang.String.valueOf()
    }
}

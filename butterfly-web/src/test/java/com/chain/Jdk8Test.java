package com.chain;

import com.chain.mode.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;
import java.util.Optional;
import java.util.function.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by lian.ran on 2018/4/10.
 */
public class Jdk8Test {
    public static final Logger logger = LoggerFactory.getLogger(Jdk8Test.class);
    public static void main(String[] args) {
        MDC.put("clientId","weweewoe");
        BiConsumer<String,String> biConsumer =(a,b)->System.out.println(a+b);
        biConsumer.accept("我是","谁");
        logger.info("测试");
        /**
         * @name 消费型接口
         * @use Consumer<T>
         * @param T 传入参数
         * @fun 接受一个参数 无返回值
         * */
        Consumer<String> con=(str)->System.out.println(str);
        con.accept("我是消费型接口!");
        /**
         * @name 供给型接口
         * @use Supplier<R>
         * @param R 返回值类型
         * @fun 无参数 有返回值
         * */
        Supplier<Date> supp=()-> new Date();
        Date date=supp.get();
        System.out.println("当前时间:"+date);

        /**
         * @name 函数型接口
         * @use Function<T,R>
         * @param T 传入参数
         * @return R 返回值类型
         * @fun 接受一个参数 有返回值
         * */
        Function<String, String> fun=(str)->"hello,"+str;
        String str=fun.apply("张俊强");
        System.out.println(str);
        /**
         * @name 断定型接口
         * @use Predicate<T>
         * @param T 传入参数
         * @return Boolean 返回一个Boolean型值
         * @fun 接受一个参数 返回Boolean型值
         * */
        Predicate<Integer> pre=(num)->num>0;
        Boolean flag=pre.test(10);
        System.out.println(flag);
        MDC.remove("clientId");

        String consumerOffsetDir = null;
        String partition = null;
        String te = null;
        //zkUtils.updatePersistentPath(topicDirs.consumerOffsetDir + "/" + topicPartition.partition, offset.toString)
        print(consumerOffsetDir+"/"+partition,te.toString());

    }

    public static void print(String consumerOffsetDir,String er){
        System.out.println(consumerOffsetDir);
    }


    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User();
        user2.setUsername("tom");
        //这里 user 对象是空的，所以返回了作为默认值的 user2。
        User result = Optional.ofNullable(user).orElse(user2);
       System.out.println(result.getUsername());
        assertEquals(user2.getUsername(), result.getUsername());
    }

    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        User user = new User();
        user.setUsername("bbd");
        User user2 = new User();
        user2.setUsername("tom");
        User result = Optional.ofNullable(user).orElse(user2);
        assertEquals("bbd", result.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {
        try {
            User user = null;
            //这里，如果 user 值为 null，会抛出 IllegalArgumentException。
          //  这个方法让我们有更丰富的语义，可以决定抛出什么样的异常，而不总是抛出 NullPointerException。
            User result = Optional.ofNullable(user)
                    .orElseThrow( () -> new IllegalArgumentException("参数错误"));

        } catch (IllegalArgumentException e) {
            System.out.println("异常");
            e.printStackTrace();
        }
    }

    @Test
    public void whenMap_thenOk() {
        User user = new User();
        user.setEmail("anna@gmail.com");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");
        assertEquals(email, user.getEmail());
    }

}

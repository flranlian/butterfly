package com.chain;

import com.chain.kafka.KafkaProducer;
import com.chain.kafka.VideoCosConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lian.ran on 2018/3/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ButterflyWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class KafkaTest {

    @Autowired
    KafkaProducer kaProducer;
    @Autowired
    VideoCosConsumer videoCosConsumer;

    @Test
    public void testSend(){
        kaProducer.sendMessage("prod_client","跟着kafka一起玩会 good bye");
    }

    public void testKStreamBuilder(){
        //KStreamBuilder streamBuilder = new KStreamBuilder();
    }
}

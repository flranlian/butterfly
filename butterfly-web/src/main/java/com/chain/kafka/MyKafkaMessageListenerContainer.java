package com.chain.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.stereotype.Component;

/**
 * Created by lian.ran on 2018/3/29.
 */
public class MyKafkaMessageListenerContainer extends KafkaMessageListenerContainer {


    public void init(){

    }
    public MyKafkaMessageListenerContainer(ConsumerFactory consumerFactory, ContainerProperties containerProperties) {
        super(consumerFactory, containerProperties);
    }
}

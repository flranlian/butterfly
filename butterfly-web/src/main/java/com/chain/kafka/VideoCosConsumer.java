package com.chain.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.BatchMessageListener;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class VideoCosConsumer {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"prod_client"},group="butterfly")
    public void consumerMessage(String message) {
       // KafkaMessageListenerContainer
         logger.info("on message:{}", message);
    }


}
package com.chain.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 适应用手动提交offset
 * 参考地址
 * https://blog.csdn.net/u012961566/article/details/77336296
 */
@Component
public class KafkaMessageListener implements AcknowledgingMessageListener<Integer, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageListener.class);

    ThreadPoolTaskExecutor threadPool;
    @Override
    public void onMessage(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
        //TODO 这里具体实现个人业务逻辑

        LOGGER.info("开始从topic{}消费数据");
        if(record!=null){
            LOGGER.info("开始从topic{}消费数据,key{},value{}",record.topic(),record.key(),record.value());
        }
        // 最后 调用acknowledgment的ack方法，提交offset
        acknowledgment.acknowledge();
    }
}
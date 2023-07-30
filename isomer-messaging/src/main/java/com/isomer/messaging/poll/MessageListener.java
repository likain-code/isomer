package com.isomer.messaging.poll;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/30 14:31
 */
@Component
public class MessageListener {

    @KafkaListener(id = "test", topics = {"test"})
    public void invoke(ConsumerRecord<String, String> record) {
        System.out.println("key: " + record.key());
        System.out.println("value: " + record.value());
    }
}

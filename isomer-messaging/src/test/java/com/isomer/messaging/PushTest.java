package com.isomer.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/30 14:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushTest {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void push() {
        kafkaTemplate.send("testTopic", "color");
    }
}

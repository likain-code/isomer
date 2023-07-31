package com.isomer.messaging.kafka;

import com.isomer.messaging.kafka.context.KafkaConsumerContext;
import com.isomer.messaging.kafka.factory.DynamicKafkaConsumerFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 09:49
 */
// @Component
public class AppTest implements ApplicationRunner {

    // @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    // @Autowired
    private DynamicKafkaConsumerFactory factory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        KafkaConsumer<Object, Object> consumer = factory.create("one", "default");
        KafkaConsumerContext.addConsumerTask("default", consumer);

        kafkaTemplate.send("one", "color", "red");
    }
}

package com.isomer.messaging.kafka.factory;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 15:50
 */
// @Component
public class DynamicKafkaConsumerFactory {

    // @Autowired
    private KafkaProperties kafkaProperties;
    // @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeSerializerClassName;
    // @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeSerializerClassName;

    public <K, V> KafkaConsumer<K, V> create(String topic, String groupId) throws ClassNotFoundException {
        Properties consumerProperties = new Properties();
        // 设定一些关于新的消费者的配置信息
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        // 设定新的消费者的组名
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 设定反序列化方式
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Class.forName(keyDeSerializerClassName));
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Class.forName(valueDeSerializerClassName));
        // 设定信任所有类型以反序列化
        consumerProperties.put("spring.json.trusted.packages", "*");
        // 新建一个消费者
        KafkaConsumer<K, V> consumer = new KafkaConsumer<>(consumerProperties);
        // 使这个消费者订阅对应话题
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }

    public <K, V> KafkaConsumer<K, V> create(Collection<String> topics, String groupId) throws ClassNotFoundException {
        Properties consumerProperties = new Properties();
        // 设定一些关于新的消费者的配置信息
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        // 设定新的消费者的组名
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 设定反序列化方式
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Class.forName(keyDeSerializerClassName));
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Class.forName(valueDeSerializerClassName));
        // 设定信任所有类型以反序列化
        consumerProperties.put("spring.json.trusted.packages", "*");
        // 新建一个消费者
        KafkaConsumer<K, V> consumer = new KafkaConsumer<>(consumerProperties);
        // 使这个消费者订阅对应话题
        consumer.subscribe(topics);
        return consumer;
    }
}

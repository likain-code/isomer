package com.isomer.messaging.kafka.context;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 15:54
 */
public class KafkaConsumerContext {

    /**
     * 存放所有自己创建的Kafka消费者任务
     * key: groupId
     * value: kafka消费者任务
     */
    private static final Map<String, KafkaConsumer<?, ?>> consumerMap = new ConcurrentHashMap<>();

    /**
     * 存放所有定时任务的哈希表
     * key: groupId
     * value: 定时任务对象，用于定时执行kafka消费者的消息消费任务
     */
    private static final Map<String, ScheduledFuture<?>> scheduleMap = new ConcurrentHashMap<>();

    /**
     * 任务调度器，用于定时任务
     */
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(24);

    /**
     * 添加一个Kafka消费者任务
     *
     * @param groupId  消费者的组名
     * @param consumer 消费者对象
     * @param <K>      消息键类型
     * @param <V>      消息值类型
     */
    public static <K, V> void addConsumerTask(String groupId, KafkaConsumer<K, V> consumer) {
        // 先存入消费者以便于后续管理
        consumerMap.put(groupId, consumer);
        // 创建定时任务，每隔1s拉取消息并处理
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            // 每次执行拉取消息之前，先检查订阅者是否已被取消（如果订阅者不存在于订阅者列表中说明被取消了）
            // 因为Kafka消费者对象是非线程安全的，因此在这里把取消订阅的逻辑和拉取并处理消息的逻辑写在一起并放入定时器中，判断列表中是否存在消费者对象来确定是否取消任务
            if (!consumerMap.containsKey(groupId)) {
                // 取消订阅并关闭消费者
                consumer.unsubscribe();
                consumer.close();
                // 关闭定时任务
                scheduleMap.remove(groupId).cancel(true);
                return;
            }
            // 拉取消息
            ConsumerRecords<K, V> records = consumer.poll(Duration.ofMinutes(1));
            for (ConsumerRecord<K, V> record : records) {
                // 自定义处理每次拉取的消息逻辑
                System.out.println("收到消息: " + record.value());
            }
        }, 0, 1, TimeUnit.SECONDS);
        // 将任务存入对应的列表以后续管理
        scheduleMap.put(groupId, future);
    }

    public static KafkaConsumer<?, ?> getConsumer(String groupId) {
        return consumerMap.get(groupId);
    }

    /**
     * 移除Kafka消费者定时任务并关闭消费者订阅
     *
     * @param groupId 消费者的组名
     */
    public static void removeConsumerTask(String groupId) {
        if (!consumerMap.containsKey(groupId)) {
            return;
        }
        // 从列表中移除消费者
        consumerMap.remove(groupId);
    }
}

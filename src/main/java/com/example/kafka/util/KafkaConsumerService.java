package com.example.kafka.util;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerService {

    private static KafkaConsumerService INSTANCE;

    private KafkaConsumerService() {
    }

    public static KafkaConsumerService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new KafkaConsumerService();
        }
        return INSTANCE;
    }

    public ConsumerRecords<String, String> runConsumer(String topicName) {
        KafkaConsumer<String, String> consumer = createConsumer(topicName);
        consumer.poll(0);
        consumer.seekToBeginning(consumer.assignment());
        ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
        consumer.close();
        return consumerRecords;
    }

    private KafkaConsumer<String, String> createConsumer(String topicName) {
        Properties properties = KafkaPropertiesProvider.getConsumerProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topicName));
        return consumer;
    }

}
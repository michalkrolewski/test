package com.example.kafka.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaPublisherService {

    private static KafkaPublisherService INSTANCE;

    private KafkaProducer<String, Object> kafkaProducer;

    private KafkaPublisherService() {
        Properties properties = KafkaPropertiesProvider.getProducerProperties();
        kafkaProducer = new KafkaProducer<>(properties);
    }

    public static KafkaPublisherService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new KafkaPublisherService();
        }
        return INSTANCE;
    }

    public void sendOnTopic(String topicName, Object object) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topicName, object.toString());
        kafkaProducer.send(record);
    }

}

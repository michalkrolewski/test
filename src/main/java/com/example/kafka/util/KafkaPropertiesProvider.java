package com.example.kafka.util;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

public class KafkaPropertiesProvider {

    public static String KAFKA_URL = "localhost:9092";

    public static Properties getConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_URL);
        properties.put("client.id", KafkaConsumerService.class.getName() + "_" + UUID.randomUUID());
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("group.id", "test");
        return properties;
    }

    public static Properties getProducerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_URL);
        properties.put("client.id", KafkaPublisherService.class.getName() + "_" + UUID.randomUUID().toString());
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        return properties;
    }

    public static Properties getAdminProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_URL);
        props.put("client.id", KafkaAdminService.class.getName() + "_" + UUID.randomUUID().toString());
        return props;
    }
}

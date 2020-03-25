package com.example.kafka.domain;

import com.example.kafka.util.KafkaConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KafkaRepository {

    Collection<KafkaObject> getObjectsFromTopic(String topicName) {
        KafkaConsumerService consumerService = KafkaConsumerService.getINSTANCE();
        ConsumerRecords<String, String> consumerRecords = consumerService.runConsumer(topicName);
        return StreamSupport.stream(consumerRecords.spliterator(), false)
                .map(ConsumerRecord::value)
                .map(this::mapToTestEntity)
                .collect(Collectors.toList());
    }

    private KafkaObject mapToTestEntity(String object) {
        try {
            return new ObjectMapper().readValue(object, KafkaObject.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}

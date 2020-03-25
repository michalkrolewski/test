package com.example.kafka.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

public class KafkaAdminService {

    private static KafkaAdminService INSTANCE;

    private AdminClient adminClient;

    private KafkaAdminService() {
        Properties props = KafkaPropertiesProvider.getAdminProperties();
        adminClient = AdminClient.create(props);
    }

    public static KafkaAdminService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new KafkaAdminService();
        }
        return INSTANCE;
    }

    public void createTopic(String topicName) {
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        adminClient.createTopics(Collections.singleton(newTopic));
    }

    public void deleteTopic(String topicName) {
        adminClient.deleteTopics(Collections.singletonList(topicName));
    }

    public Set<String> getTopics() {
        try {
            return adminClient.listTopics().names().get();
        } catch (Exception e) {
            throw new IllegalStateException("Cannot find topics.");
        }
    }
}